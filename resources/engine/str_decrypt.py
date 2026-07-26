# -*- coding: utf-8 -*-
"""
Расшифровка строковых литералов для ОДНОЙ конкретной, узнаваемой схемы
обфускатора - портировано с Rust-инструмента пользователя (decryptor.rs +
crypto.rs), переписано на чистый Python (свой AES - см. aes128.py, внешних
криптобиблиотек в проекте принципиально нет).

СХЕМА (как есть у этого конкретного обфускатора, НЕ общий случай):
  - В классе-расшифровщике в constant pool встречается Utf8-строка
    "AES/ECB/PKCS5Padding" (маркер) - используется как быстрый признак:
    "это точно та самая схема".
  - В классе есть два int-поля с ConstantValue (h, l) - зерно для ключа.
  - buildCipher(h, l): 8 байт (big-endian h, затем big-endian l) -> SHA-256
    -> первые 16 байт как AES-128 ключ.
  - Сама расшифровка: строка на входе - Base64, AES-128-ECB, PKCS7-паддинг.

Это НЕ общий "исполни произвольный decrypt-метод" (такой подход рискованнее
и не сделан в этой сессии - см. HANDOFF) - это узнавание ОДНОЙ конкретной,
задокументированной схемы по чёткому маркеру. Если маркер не найден -
модуль ничего не делает, никаких попыток угадать/подобрать ключ.

ВАЖНО про формат данных: classfile.py::ClassFile.pool - словарь
{index: tuple}, например ("Utf8", "строка"), ("Integer", 42) - НЕ объекты/
словари с полями. Field.constant_value - тоже такой кортеж или None.
"""
import base64
import hashlib
import struct

from aes128 import aes128_ecb_decrypt, unpad_pkcs7

MARKER_UTF8 = "AES/ECB/PKCS5Padding"

# Состояние "текущий обнаруженный расшифровщик для этого jar'а" - читается
# напрямую из stackvm.py при обработке invokestatic (см. main.py, где это
# выставляется ПЕРЕД основным циклом декомпиляции классов и сбрасывается в
# None на каждый новый jar - на случай --api-server, обрабатывающего
# несколько jar подряд в одном процессе). Простой module-level словарь, а
# не проброс параметра через все сигнатуры MethodCtx/decompile_method_body -
# обработка классов строго последовательная (без потоков/multiprocessing,
# проверено по всему main.py), так что это безопасно и намного менее
# инвазивно, чем менять сигнатуры в 4+ местах вызова.
# Формат: {"owner": "com/example/Decryptor", "method": "decrypt", "key": b"..."} или None.
ACTIVE = None
DECRYPTED_COUNT = 0


def has_marker(cf):
    """cf - объект ClassFile (см. classfile.py). True, если в constant pool
    встречается точная строка-маркер этой схемы."""
    for entry in cf.pool.values():
        if entry and entry[0] == "Utf8" and entry[1] == MARKER_UTF8:
            return True
    return False


def build_key(h, l):
    """h, l - 32-битные int (со знаком, как в Java). Собирает 8-байтовый seed
    (big-endian h, потом big-endian l), SHA-256, первые 16 байт - ключ AES-128."""
    seed = struct.pack(">ii", h, l)
    digest = hashlib.sha256(seed).digest()
    return digest[:16]


def decrypt_string(key16, encoded):
    """encoded - строка в Base64 (как встречается в байткоде, LDC-константа).
    Возвращает расшифрованную строку или None, если что-то не сошлось
    (некорректный base64, длина не кратна 16, паддинг не бьётся) - НЕ
    бросает исключение наружу, вызывающий код просто оставляет оригинал
    как есть (честный отказ, а не угадывание)."""
    try:
        raw = base64.b64decode(encoded, validate=True)
    except Exception:
        return None
    if not raw or len(raw) % 16 != 0:
        return None
    try:
        decrypted_padded = aes128_ecb_decrypt(raw, key16)
        plain = unpad_pkcs7(decrypted_padded)
        return plain.decode("utf-8")
    except Exception:
        return None


def find_decryptor_in_class(cf):
    """Если этот класс - тот самый расшифровщик (есть маркер), извлекает
    (h, l) из его int-полей с ConstantValue (кортеж ("Integer", value)).
    Возвращает 16-байтовый ключ или None, если это не расшифровщик/поля не
    нашлись в ожидаемом виде."""
    if not has_marker(cf):
        return None
    ints = []
    for f in cf.fields:
        cv = f.constant_value
        if cv and cv[0] == "Integer":
            ints.append(cv[1])
    if len(ints) < 2:
        return None
    # Берём первые два найденных int-поля с ConstantValue - соответствует
    # порядку полей в оригинальном классе (h объявлено раньше l).
    return build_key(ints[0], ints[1])


def decrypt_method_name(cf):
    """Имя статического метода (String)->String в классе-расшифровщике -
    используется потом, чтобы найти МЕСТА ВЫЗОВА этого метода в остальном
    коде проекта (invokestatic на этот метод -> аргумент - зашифрованная
    строка, результат вызова можно заменить на расшифрованный литерал)."""
    for m in cf.methods:
        if (m.access & 0x0008) and m.descriptor == "(Ljava/lang/String;)Ljava/lang/String;":
            return m.name
    return None
