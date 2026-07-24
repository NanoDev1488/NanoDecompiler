# -*- coding: utf-8 -*-
"""
Восстановление / генерация pom.xml для jar-плагина.

Источники данных, в порядке приоритета:
  1. META-INF/maven/<group>/<artifact>/pom.xml   - если maven положил туда ОРИГИНАЛЬНЫЙ
     pom.xml (так бывает не всегда) - просто копируем его как "восстановленный оригинал".
  2. META-INF/maven/<group>/<artifact>/pom.properties - почти всегда есть в maven-сборках,
     содержит groupId/artifactId/version - то есть точные координаты проекта.
  3. plugin.yml (Bukkit/Spigot/Paper) - name/version/api-version, а также секция
     "libraries:" (Paper 1.19+) - это ГОТОВЫЕ maven-координаты зависимостей, бери и используй.
  4. Если ничего из выше нет - fallback на имя jar-файла для artifactId/version.

Дополнительно: сканируем все "внешние" классы (упомянутые в byte-коде, но не
входящие в сам jar) и по префиксу пакета сопоставляем с известными популярными
библиотеками (эвристика по таблице ниже) - чтобы предположить dependencies,
которые НЕ были явно перечислены в libraries/pom.properties.
"""
import os
import re

# top-level package prefix -> (groupId, artifactId, комментарий)
KNOWN_LIBS = [
    ("org.bukkit",              ("org.spigotmc", "spigot-api", None)),
    ("org.spigotmc",             ("org.spigotmc", "spigot-api", None)),
    ("com.destroystokyo.paper", ("io.papermc.paper", "paper-api", None)),
    ("io.papermc",              ("io.papermc.paper", "paper-api", None)),
    ("net.md_5.bungee",         ("net.md-5", "bungeecord-chat", None)),
    ("net.kyori.adventure",     ("net.kyori", "adventure-api", None)),
    ("com.google.common",       ("com.google.guava", "guava", None)),
    ("com.google.thirdparty",   ("com.google.guava", "guava", "вспомогательные классы внутри guava (напр. publicsuffix)")),
    ("io.reactivex.rxjava3",    ("io.reactivex.rxjava3", "rxjava", None)),
    ("com.google.gson",         ("com.google.code.gson", "gson", None)),
    ("com.google.inject",       ("com.google.inject", "guice", "shaded DI framework")),
    ("com.fasterxml.jackson",   ("com.fasterxml.jackson.core", "jackson-databind", None)),
    ("org.yaml.snakeyaml",      ("org.yaml", "snakeyaml", None)),
    ("org.apache.commons.lang3",("org.apache.commons", "commons-lang3", None)),
    ("org.apache.commons.io",   ("commons-io", "commons-io", None)),
    ("org.slf4j",               ("org.slf4j", "slf4j-api", None)),
    ("com.zaxxer.hikari",       ("com.zaxxer", "HikariCP", None)),
    ("org.sqlite",              ("org.xerial", "sqlite-jdbc", None)),
    ("com.mysql",                ("com.mysql", "mysql-connector-j", None)),
    ("io.netty",                ("io.netty", "netty-all", None)),
    ("org.jetbrains.annotations",("org.jetbrains", "annotations", None)),
    ("javax.annotation",        ("javax.annotation", "jsr305", None)),
    ("com.mojang.brigadier",    ("com.mojang", "brigadier", None)),
    ("com.mojang.authlib",      ("com.mojang", "authlib", None)),
    ("org.enginehub.piston",    ("org.enginehub.piston", "piston-core", "часть EngineHub Piston (WorldEdit)")),
    ("com.sk89q.worldedit",     ("com.sk89q.worldedit", "worldedit-core", None)),
    ("com.sk89q.worldguard",    ("com.sk89q.worldguard", "worldguard-core", None)),
    ("org.bstats",              ("org.bstats", "bstats-bukkit", "статистика/телеметрия (bStats)")),
    ("redis.clients.jedis",     ("redis.clients", "jedis", None)),
    ("com.zaxxer",               ("com.zaxxer", "HikariCP", None)),
    ("org.bouncycastle",        ("org.bouncycastle", "bcprov-jdk18on", None)),
    ("okhttp3",                 ("com.squareup.okhttp3", "okhttp", None)),
    ("okio",                    ("com.squareup.okio", "okio", None)),
    ("retrofit2",               ("com.squareup.retrofit2", "retrofit", None)),
    ("org.apache.commons.codec", ("commons-codec", "commons-codec", None)),
    ("org.apache.commons.collections4", ("org.apache.commons", "commons-collections4", None)),
    ("com.mongodb",             ("org.mongodb", "mongodb-driver-sync", None)),
    ("org.bson",                ("org.mongodb", "bson", None)),
    ("ch.qos.logback",          ("ch.qos.logback", "logback-classic", None)),
    ("org.apache.http",         ("org.apache.httpcomponents", "httpclient", None)),
    ("kotlin",                  ("org.jetbrains.kotlin", "kotlin-stdlib", None)),
    ("kotlinx",                 ("org.jetbrains.kotlinx", "kotlinx-coroutines-core", None)),
    ("com.typesafe.config",     ("com.typesafe", "config", None)),
    ("it.unimi.dsi.fastutil",   ("it.unimi.dsi", "fastutil", None)),
    ("org.mariadb",             ("org.mariadb.jdbc", "mariadb-java-client", None)),
    ("com.google.protobuf",     ("com.google.protobuf", "protobuf-java", None)),
    ("com.google.errorprone.annotations", ("com.google.errorprone", "error_prone_annotations", None)),
    ("javassist",                ("org.javassist", "javassist", None)),
    ("com.github.benmanes.caffeine", ("com.github.ben-manes.caffeine", "caffeine", None)),
    ("org.apache.commons.math3", ("org.apache.commons", "commons-math3", None)),
    ("org.reflections",          ("org.reflections", "reflections", None)),
    ("com.cryptomorin.xseries",  ("com.github.cryptomorin", "XSeries",
                                   "обычно шейдится напрямую из исходников (JitPack), не из Maven Central - "
                                   "проверить актуальный groupId/repository перед использованием pom.xml")),
]

# artifactId'ы известных БИБЛИОТЕК (не самого плагина) - используется, чтобы НЕ
# перепутать pom.xml/pom.properties шейднутой внутрь зависимости (напр.
# sqlite-jdbc) с pom.xml самого плагина, если внутри jar их несколько сразу
# (см. find_pom_properties_and_xml ниже - реальный кейс, найден пользователем:
# EryBuyer-v1.jar шейдит sqlite-jdbc, внутри лежит ЕГО pom.xml с <release>8</release>,
# который раньше ошибочно принимался за pom.xml самого плагина).
_KNOWN_LIB_ARTIFACT_IDS = {a for _, (g, a, c) in KNOWN_LIBS}

IGNORED_PREFIXES = ("java.", "javax.", "jdk.", "sun.", "com.sun.")


def _detect_lib(dotted_name):
    for prefix, coords in KNOWN_LIBS:
        if dotted_name == prefix or dotted_name.startswith(prefix + "."):
            return coords
    return None


def parse_shade_relocations(pom_xml_text):
    """Разбирает конфиг maven-shade-plugin <relocations> из текста pom.xml:
    список (pattern, shadedPattern) - т.е. "оригинальный пакет библиотеки"
    -> "во что его переименовали при шейдинге внутрь плагина". Нужно, чтобы
    узнавать бандленные библиотеки, даже если их пакет релоцирован (напр.
    org.sqlite -> com.agent1k.libs.sqlite) - простое сравнение префикса
    пакета с KNOWN_LIBS в этом случае не сработало бы (см. main.py -
    _known_library_coords). Регулярка, а не полноценный XML-парсер - в духе
    остального кода этого файла (find_pom_properties_and_xml тоже так же
    вытаскивает artifactId)."""
    if not pom_xml_text:
        return []
    result = []
    for block in re.findall(r"<relocation>(.*?)</relocation>", pom_xml_text, re.S):
        m_pat = re.search(r"<pattern>([^<]+)</pattern>", block)
        m_shaded = re.search(r"<shadedPattern>([^<]+)</shadedPattern>", block)
        if m_pat and m_shaded:
            result.append((m_pat.group(1).strip(), m_shaded.group(1).strip()))
    return result


def find_pom_properties_and_xml(uploads_zip_names, zip_reader):
    """Ищем META-INF/maven/<group>/<artifact>/{pom.properties,pom.xml} внутри jar.

    Шейднутый (fat/uber) jar обычно содержит НЕСКОЛЬКО таких пар - по одной на
    каждую бандленную библиотеку maven-сборки, а не только для самого плагина
    (а иногда и вовсе без записи для самого плагина, если shade её не сохранил).
    Раньше здесь бралась ПОСЛЕДНЯЯ подходящая запись без разбора чья она -
    что приводило к путанице: pom.xml библиотеки (напр. sqlite-jdbc, со своим
    <release>8</release>) ошибочно принимался за pom.xml самого плагина.
    Теперь: собираем ВСЕ найденные пары и выбираем ту, чей artifactId НЕ
    совпадает ни с одной известной библиотекой (см. _KNOWN_LIB_ARTIFACT_IDS) -
    это и есть, предположительно, сам плагин. Если все найденные записи
    оказались известными библиотеками (или записи вообще нет) - возвращаем
    None, None: пусть pom.xml будет честно СГЕНЕРИРОВАН по эвристике, а не
    взят "оригиналом" от чужой зависимости."""
    by_dir = {}
    for n in uploads_zip_names:
        m = re.match(r"(META-INF/maven/.+)/pom\.properties$", n)
        if m:
            by_dir.setdefault(m.group(1), {})["properties"] = n
            continue
        m = re.match(r"(META-INF/maven/.+)/pom\.xml$", n)
        if m:
            by_dir.setdefault(m.group(1), {})["xml"] = n

    candidates = []
    for _dir, entry in by_dir.items():
        props = None
        if "properties" in entry:
            try:
                raw = zip_reader.read(entry["properties"]).decode("utf-8", errors="replace")
                props = {}
                for line in raw.splitlines():
                    line = line.strip()
                    if not line or line.startswith("#") or "=" not in line:
                        continue
                    k, v = line.split("=", 1)
                    props[k.strip()] = v.strip()
            except Exception:
                props = None
        xml_text = None
        if "xml" in entry:
            try:
                xml_text = zip_reader.read(entry["xml"]).decode("utf-8", errors="replace")
            except Exception:
                xml_text = None
        candidates.append((props, xml_text))

    if not candidates:
        return None, None

    def _artifact_id(props, xml_text):
        if props and "artifactId" in props:
            return props["artifactId"]
        if xml_text:
            m = re.search(r"<artifactId>([^<]+)</artifactId>", xml_text)
            if m:
                return m.group(1).strip()
        return None

    own = [(p, x) for (p, x) in candidates if _artifact_id(p, x) not in _KNOWN_LIB_ARTIFACT_IDS]
    if own:
        # Предпочитаем запись, где есть И pom.properties, И pom.xml
        own.sort(key=lambda px: 0 if (px[0] and px[1]) else 1)
        return own[0]
    return None, None


def parse_plugin_yml(text):
    """Очень простой ручной парсер нужных нам полей из plugin.yml (без пакета pyyaml)."""
    info = {"name": None, "version": None, "api_version": None, "main": None,
            "libraries": [], "depend": [], "softdepend": [], "website": None, "authors": []}
    if not text:
        return info
    lines = text.splitlines()
    i = 0
    while i < len(lines):
        line = lines[i]
        stripped = line.strip()
        m = re.match(r'^(name|version|api-version|main|website)\s*:\s*(.+)$', stripped)
        if m:
            key, val = m.group(1), m.group(2).strip()
            val = val.strip('"\'')
            if key == "api-version":
                info["api_version"] = val
            else:
                info[key] = val
            i += 1
            continue
        m = re.match(r'^authors\s*:\s*\[(.*)\]$', stripped)
        if m:
            info["authors"] = [a.strip(' "\'') for a in m.group(1).split(",") if a.strip()]
            i += 1
            continue
        if re.match(r'^libraries\s*:\s*$', stripped):
            i += 1
            while i < len(lines) and re.match(r'^\s*-\s*', lines[i]):
                item = re.sub(r'^\s*-\s*', '', lines[i]).strip(' "\'')
                info["libraries"].append(item)
                i += 1
            continue
        if re.match(r'^(depend|softdepend)\s*:\s*\[(.*)\]$', stripped):
            m2 = re.match(r'^(depend|softdepend)\s*:\s*\[(.*)\]$', stripped)
            key = m2.group(1)
            info[key] = [a.strip(' "\'') for a in m2.group(2).split(",") if a.strip()]
            i += 1
            continue
        i += 1
    return info


def guess_group_artifact(jar_basename, plugin_info, pom_props):
    if pom_props and "groupId" in pom_props and "artifactId" in pom_props:
        return pom_props["groupId"], pom_props["artifactId"], pom_props.get("version", "1.0")
    name = plugin_info.get("name") or re.sub(r"[-_][\d.]+$", "", jar_basename)
    version = plugin_info.get("version") or "1.0"
    group = "com.example." + re.sub(r"[^a-zA-Z0-9]", "", name).lower()
    return group, name, version


def build_pom(jar_path, plugin_yml_text, external_dotted_names, uploads_zip_names, zip_reader):
    jar_basename = os.path.splitext(os.path.basename(jar_path))[0]
    plugin_info = parse_plugin_yml(plugin_yml_text)
    pom_props, original_pom_xml = find_pom_properties_and_xml(uploads_zip_names, zip_reader)

    if original_pom_xml:
        comment = (
            "<!-- Это ОРИГИНАЛЬНЫЙ pom.xml, найденный внутри jar по пути "
            "META-INF/maven/*/pom.xml - не восстановление, а точная копия. -->\n"
        )
        text = original_pom_xml
        # <?xml version="1.0"?> ОБЯЗАНА быть самой первой строкой документа -
        # комментарий нужно вставлять ПОСЛЕ неё, а не перед (иначе невалидный
        # XML: "processing instruction can not have PITarget" при парсинге).
        stripped = text.lstrip("\ufeff \t\r\n")
        if stripped.startswith("<?xml"):
            end = stripped.find("?>")
            if end != -1:
                decl = stripped[:end + 2]
                rest = stripped[end + 2:].lstrip("\r\n")
                text = decl + "\n" + comment + rest
            else:
                text = comment + text
        else:
            text = comment + text
        return text, "original"

    group, artifact, version = guess_group_artifact(jar_basename, plugin_info, pom_props)

    # Явные зависимости из plugin.yml -> libraries (Paper-формат "group:artifact:version")
    explicit_deps = []
    for lib in plugin_info.get("libraries", []):
        parts = lib.split(":")
        if len(parts) == 3:
            explicit_deps.append((parts[0], parts[1], parts[2], "явно указано в plugin.yml libraries:"))

    # Угаданные зависимости по внешним пакетам, реально встреченным в байткоде
    guessed = {}
    for dotted in external_dotted_names:
        if dotted.startswith(IGNORED_PREFIXES):
            continue
        coords = _detect_lib(dotted)
        if coords:
            key = (coords[0], coords[1])
            guessed[key] = coords[2]

    explicit_keys = {(g, a) for g, a, v, c in explicit_deps}
    # spigot-api/paper-api уже добавляется отдельно как "provided" API-зависимость -
    # не дублируем её в угаданном списке.
    explicit_keys |= {
        ("org.spigotmc", "spigot-api"),
        ("io.papermc.paper", "paper-api"),
        ("com.destroystokyo.paper", "paper-api"),
    }

    dep_xml_parts = []
    for g, a, v, comment in explicit_deps:
        dep_xml_parts.append(
            f"        <!-- {comment} -->\n"
            f"        <dependency>\n"
            f"            <groupId>{g}</groupId>\n"
            f"            <artifactId>{a}</artifactId>\n"
            f"            <version>{v}</version>\n"
            f"        </dependency>"
        )
    for (g, a), comment in guessed.items():
        if (g, a) in explicit_keys:
            continue
        note = f" ({comment})" if comment else ""
        dep_xml_parts.append(
            f"        <!-- ПРЕДПОЛОЖЕНИЕ по обнаруженным импортам{note} - версию нужно подобрать вручную -->\n"
            f"        <dependency>\n"
            f"            <groupId>{g}</groupId>\n"
            f"            <artifactId>{a}</artifactId>\n"
            f"            <version>REPLACE_ME</version>\n"
            f"        </dependency>"
        )

    deps_block = "\n".join(dep_xml_parts) if dep_xml_parts else "        <!-- зависимостей не обнаружено -->"

    # Если нашлись бандлящиеся зависимости (не только provided spigot-api) -
    # добавляем maven-shade-plugin, иначе `mvn package` соберёт jar БЕЗ них
    # (скомпилируется нормально, но упадёт в рантайме - ClassNotFoundException).
    build_block = ""
    if guessed:
        build_block = """
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-shade-plugin</artifactId>
                <version>3.5.1</version>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals><goal>shade</goal></goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
"""

    api_version = plugin_info.get("api_version") or "1.13"

    pom = f"""<?xml version="1.0" encoding="UTF-8"?>
<!--
  ЭТО СГЕНЕРИРОВАННЫЙ (восстановленный по метаданным) pom.xml, а НЕ оригинал автора.
  Источники: {"pom.properties внутри jar" if pom_props else "plugin.yml + эвристика по импортам"}.
  Версии зависимостей, отмеченных REPLACE_ME, нужно подобрать вручную (в байткоде
  версия библиотеки не хранится - только имена пакетов/классов).
-->
<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>{group}</groupId>
    <artifactId>{artifact}</artifactId>
    <version>{version}</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>17</maven.compiler.source>
        <maven.compiler.target>17</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>

    <repositories>
        <repository>
            <id>spigotmc-repo</id>
            <url>https://hub.spigotmc.org/nexus/content/repositories/snapshots/</url>
        </repository>
        <repository>
            <id>papermc</id>
            <url>https://repo.papermc.io/repository/maven-public/</url>
        </repository>
    </repositories>

    <dependencies>
        <!-- api-version из plugin.yml: {api_version} -->
        <dependency>
            <groupId>org.spigotmc</groupId>
            <artifactId>spigot-api</artifactId>
            <version>{api_version}-R0.1-SNAPSHOT</version>
            <scope>provided</scope>
        </dependency>
{deps_block}
    </dependencies>
{build_block}</project>
"""
    return pom, "generated"
