---
source_url: https://en.cppreference.com/w/cpp/language/memory_model
title: cppreference: C++ Memory Model and Data Races
downloaded_at_utc: 2026-09-30T07:56:26.920011+00:00
http_status: 200
content_sha256: 7b9d16d30a37b227e6be878b4f0997da4fa01fa16129a4fad26450077466ada9
category: cpp_architecture
---

# cppreference: C++ Memory Model and Data Races

**Официальный первоисточник:** [https://en.cppreference.com/w/cpp/language/memory_model](https://en.cppreference.com/w/cpp/language/memory_model)  
**Статус загрузки:** HTTP 200 OK  
**Хэш содержимого (SHA-256):** `7b9d16d30a37b227e6be878b4f0997da4fa01fa16129a4fad26450077466ada9`  

---


Memory model - cppreference.com

cppreference.com

Search

&#x1F50D;

Create account

Log in

Namespaces     Page
Discussion

Variants

Views     Read
View source
View history

Actions

# Memory model

From cppreference.com

<   cpp   |   language

&#160;   C++
Compiler support
Freestanding and hosted
Language
Standard library
Standard library headers
Named requirements
Feature test macros   (C++20)
Language support library
Concepts library   (C++20)
Diagnostics library
Memory management library
Metaprogramming library   (C++11)
General utilities library
Containers library
Iterators library
Ranges library   (C++20)
Algorithms library
Strings library
Text processing library
Numerics library
Date and time library
Input/output library
Filesystem library   (C++17)
Concurrency support library   (C++11)
Execution control library   (C++26)
Technical specifications
Symbols index
External libraries
&#91;edit&#93;       &#160;   C++ language

General topics

Preprocessor
Comments

Keywords
Escape sequences

Flow control
Conditional execution statements

if

switch

Iteration statements (loops)

for
range-`for`   (C++11)

while
`do-while`

Jump statements

continue
goto

break
return

Functions
Function declaration
Lambda function expression
`inline` specifier
Dynamic exception specifications   ( until C++17* )
`noexcept` specifier   (C++11)
Exceptions

`throw`-expression
`try` block

`catch` handler

Namespaces

Namespace declaration `&#160;&#160;`

Namespace aliases

Types

Fundamental types
Enumeration types
Function types

Class/struct types
Union types

Specifiers

`const`/`volatile`
decltype    (C++11)
auto    (C++11)

constexpr    (C++11)
consteval    (C++20)
constinit    (C++20)

Storage duration specifiers
Initialization

Default-initialization
Value-initialization
Zero-initialization
Copy-initialization
Direct-initialization

Aggregate initialization
List-initialization   (C++11) `&#160;&#160;`
Constant initialization
Reference initialization

Expressions

Value categories
Order of evaluation

Operators
Operator precedence

Alternative representations
Literals
Boolean  -  Integer  -  Floating-point
Character  -  String  -   nullptr    (C++11)
User-defined   (C++11)
Utilities
Attributes   (C++11)
Types
`typedef` declaration
Type alias declaration   (C++11)
Casts

Implicit conversions
static_cast
const_cast

Explicit conversions
dynamic_cast
reinterpret_cast

Memory allocation

`new` expression

`delete` expression

Classes

Class declaration
Constructors
`this` pointer

Access specifiers
`friend` specifier

Class-specific function properties

Virtual function
`override` specifier   (C++11) `&#160;&#160;`
`final` specifier   (C++11)

explicit    (C++11)
static

Special member functions

Default constructor
Copy constructor
Move constructor   (C++11)

Copy assignment
Move assignment   (C++11)
Destructor

Templates

Class template
Function template

Template specialization
Parameter packs   (C++11)

Miscellaneous

Inline assembly

History of C++

&#91;edit&#93;       &#160;    Basic Concepts

Comments
ASCII
Punctuation
Names and identifiers
Types
Fundamental types
Objects
Scope
Object lifetime
Storage duration and linkage
Definitions and ODR
Name lookup
Qualified name lookup
Unqualified name lookup
The as-if rule
Undefined behavior
Memory model
Multi-threaded executions and data races   (C++11)
Character sets and encodings
Phases of translation
The `main` function
Modules   (C++20)
Contracts   (C++26)

&#91;edit&#93;       &#160;

Defines the semantics of computer memory storage for the purpose of the C++ abstract machine.

The memory available to a C++ program is one or more contiguous sequences of  bytes . Each byte in memory has a unique  address .

## Contents

1   Byte
2   Memory location
3   Defect reports
4   See also

###  Byte

A  byte  is the smallest addressable unit of memory. It is defined as a contiguous sequence of bits, large enough to hold

the value of any `UTF-8` code unit (256 distinct values) and of

any member of the  basic execution character set .

(until C++23)

the ordinary literal encoding of any element of the  basic literal character set .

(since C++23)

Similar to C, C++ supports bytes of sizes 8 bits and greater.

The  types  ` char `, ` unsigned     char `, and ` signed     char ` use one byte for both storage and  value representation . The number of bits in a byte is accessible as   CHAR_BIT   or  ` std  ::  numeric_limits  <  unsigned     char  >::  digits ` .

###  Memory location

A  memory location  is the storage occupied by the  object representation  of either an object of  scalar type  that is not a  bit-field , or the largest contiguous sequence of bit-fields of non-zero length.

Note: Various features of the language, such as  references  and  virtual functions , might involve additional memory locations that are not accessible to programs but are managed by the implementation.

```
struct     S
{
char     a  ;         // memory location #1
int     b     :     5  ;      // memory location #2
int     c     :     11  ,     // memory location #2 (continued)
:     0  ,
d     :     8  ;      // memory location #3
struct
{
int     ee     :     8  ;     // memory location #4
}     e  ;
}     obj  ;     // The object “obj” consists of 4 separate memory locations

```

###  Defect reports

The following behavior-changing defect reports were applied retroactively to previously published C++ standards.

DR

Applied to

Behavior as published

Correct behavior

CWG 1953

C++98

objects occupying the same storage were
considered as different memory locations

memory location
now refers to storage

###  See also

C documentation   for   Memory model

Categories :    Pages using deprecated source tags    Pages using deprecated enclose attributes

Navigation

Support us                      Recent changes                      FAQ                      Offline version

Tools

What links here                      Related changes                      Upload file                      Special pages                      Printable version                      Permanent link                      Page information

In other languages

Español                      日本語                      Русский                      中文
