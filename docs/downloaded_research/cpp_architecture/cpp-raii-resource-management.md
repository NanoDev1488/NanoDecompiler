---
source_url: https://en.cppreference.com/w/cpp/language/raii
title: cppreference: RAII (Resource Acquisition Is Initialization)
downloaded_at_utc: 2026-09-30T07:56:24.866056+00:00
http_status: 200
content_sha256: 4f5badf2d6c5b86eb71cde03e232dd3447854c7505a51961af19cb056b67f31e
category: cpp_architecture
---

# cppreference: RAII (Resource Acquisition Is Initialization)

**Официальный первоисточник:** [https://en.cppreference.com/w/cpp/language/raii](https://en.cppreference.com/w/cpp/language/raii)  
**Статус загрузки:** HTTP 200 OK  
**Хэш содержимого (SHA-256):** `4f5badf2d6c5b86eb71cde03e232dd3447854c7505a51961af19cb056b67f31e`  

---


RAII - cppreference.com

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

# RAII

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

&#91;edit&#93;       &#160;

Resource Acquisition Is Initialization  or RAII, is a C++ programming technique   &#91; 1 &#93;      &#91; 2 &#93;    which binds the life cycle of a resource that must be acquired before use (allocated heap memory, thread of execution, open socket, open file, locked mutex, disk space, database connection—anything that exists in limited supply) to the  lifetime  of an object.

RAII guarantees that the resource is available to any function that may access the object (resource availability is a  class invariant , eliminating redundant runtime tests). It also guarantees that all resources are released when the lifetime of their controlling object ends, in reverse order of acquisition. Likewise, if resource acquisition fails (the constructor exits with an exception), all resources acquired by every fully-constructed member and base subobject are released in reverse order of initialization. This leverages the core language features ( object lifetime ,  scope exit ,  order of initialization  and  stack unwinding ) to eliminate resource leaks and guarantee exception safety. Another name for this technique is  Scope-Bound Resource Management  (SBRM), after the basic use case where the lifetime of an RAII object ends due to scope exit.

RAII can be summarized as follows:

encapsulate each resource into a class, where
the constructor acquires the resource and establishes all class invariants or throws an exception if that cannot be done,
the destructor releases the resource and never throws exceptions;
always use the resource via an instance of a RAII-class that either
has automatic storage duration or temporary lifetime itself, or
has lifetime that is bounded by the lifetime of an automatic or temporary object.

Move semantics enable the transfer of resources and ownership between objects, inside and outside containers, and across threads, while ensuring resource safety.

(since C++11)

Classes with `open()`/`close()`, `lock()`/`unlock()`, or `init()`/`copyFrom()`/`destroy()` member functions are typical examples of non-RAII classes:

```
std  ::  mutex     m  ;

void     bad  ()
{
m  .  lock  ();                 // acquire the mutex
f  ();                      // if f() throws an exception, the mutex is never released
if     (  !  everything_ok  ())
return  ;               // early return, the mutex is never released
m  .  unlock  ();               // if bad() reaches this statement, the mutex is released
}

void     good  ()
{
std  ::  lock_guard  <  std  ::  mutex  >     lk  (  m  );     // RAII class: mutex acquisition is initialization
f  ();                                   // if f() throws an exception, the mutex is released
if     (  !  everything_ok  ())
return  ;                            // early return, the mutex is released
}                                          // if good() returns normally, the mutex is released

```

###  The standard library

The C++ library classes that manage their own resources follow RAII:   std::string  ,   std::vector    ,   std::jthread     (since C++20)   , and many others acquire their resources in constructors (which throw exceptions on errors), release them in their destructors (which never throw), and don't require explicit cleanup.

In addition, the standard library offers several RAII wrappers to manage user-provided resources:

std::unique_ptr   and   std::shared_ptr   through   std::make_unique   and   std::make_shared   to manage dynamically-allocated memory;
std::lock_guard  ,   std::unique_lock  ,   std::shared_lock   to manage mutexes.

(since C++11)

###  Notes

RAII does not apply to the management of the resources that are not acquired before use: CPU time, core availability, cache capacity, entropy pool capacity, network bandwidth, electric power consumption, stack memory. For such resources, a C++ class constructor cannot guarantee resource availability for the duration of object lifetime, and other means of resource management have to be used.

###  External links

↑     RAII in Stroustrup's C++ FAQ

↑     C++ Core Guidelines E.6 "Use RAII to prevent leaks"

Category :    Pages using deprecated source tags

Navigation

Support us                      Recent changes                      FAQ                      Offline version

Tools

What links here                      Related changes                      Upload file                      Special pages                      Printable version                      Permanent link                      Page information

In other languages

Español                      日本語                      Русский                      中文
