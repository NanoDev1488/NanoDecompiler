---
source_url: https://en.cppreference.com/w/cpp/utility/variant
title: cppreference: std::variant and Type-Safe Sum Types for IR
downloaded_at_utc: 2026-09-30T07:56:33.058140+00:00
http_status: 200
content_sha256: 497395d99f1879228b23f68998afc3be6c0aa712db609f5d21cf7f7865646d2e
category: cpp_architecture
---

# cppreference: std::variant and Type-Safe Sum Types for IR

**Официальный первоисточник:** [https://en.cppreference.com/w/cpp/utility/variant](https://en.cppreference.com/w/cpp/utility/variant)  
**Статус загрузки:** HTTP 200 OK  
**Хэш содержимого (SHA-256):** `497395d99f1879228b23f68998afc3be6c0aa712db609f5d21cf7f7865646d2e`  

---


std::variant - cppreference.com

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

#  std:: variant

From cppreference.com

<   cpp   |   utility

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
&#91;edit&#93;       &#160;   Utilities library

Language support
Type support  (basic types, RTTI)
Library feature-test macros   (C++20)
Program utilities
Variadic functions
initializer_list        (C++11)
is_constant_evaluated        (C++20)
is_within_lifetime        (C++26)
source_location        (C++20)
Coroutine support   (C++20)
Contract support   (C++26)
Three-way comparison
three_way_comparable  three_way_comparable_with        (C++20)    (C++20)
strong_ordering        (C++20)
weak_ordering        (C++20)
partial_ordering        (C++20)
common_comparison_category        (C++20)
compare_three_way_result        (C++20)
compare_three_way        (C++20)
strong_order        (C++20)
weak_order        (C++20)
partial_order        (C++20)
compare_strong_order_fallback        (C++20)
compare_weak_order_fallback        (C++20)
compare_partial_order_fallback        (C++20) &#160;&#160;&#160;&#160;
type_order        (C++26)

is_eq  is_lt  is_lteq        (C++20)    (C++20)    (C++20)

is_neq  is_gt  is_gteq        (C++20)    (C++20)    (C++20)

General utilities

Function objects
Bit manipulation   (C++20)
bitset
hash        (C++11)

Relational operators   (deprecated in C++20)

rel_ops::operator!=  rel_ops::operator>       &#160;&#160;&#160;&#160;

rel_ops::operator<=  rel_ops::operator>=

Integer comparison functions

cmp_equal  cmp_less  cmp_less_than        (C++20)    (C++20)    (C++20) &#160;&#160;&#160;&#160;

cmp_not_equal  cmp_greater  cmp_greater_than        (C++20)    (C++20)    (C++20)

in_range        (C++20)
Swap  and  type operations

swap
ranges::swap        (C++20)
exchange        (C++14)
declval        (C++11)
to_underlying        (C++23)

forward        (C++11)
forward_like        (C++23)
move        (C++11)
move_if_noexcept        (C++11)
as_const        (C++17)

Common vocabulary types

pair
tuple        (C++11)
optional        (C++17)
any        (C++17)
variant        (C++17)

tuple_size        (C++11)
tuple_element        (C++11)
apply        (C++17)
make_from_tuple        (C++17)
expected        (C++23)

&#91;edit&#93;       &#160;    std::variant
Member functions
variant::variant
variant::~variant
variant::operator=
Observers
variant::index
variant::valueless_by_exception
Modifiers
variant::emplace
variant::swap
Visitation
variant::visit        (C++26)
Non-member functions
visit (std::variant)
holds_alternative
get (std::variant)
get_if
operator==  operator!=  operator<  operator<=  operator>  operator>=  operator<=>                    (C++20)
swap (std::variant)
Helper classes
monostate
bad_variant_access
variant_size
variant_alternative
hash <std::variant>
Helper objects
variant_npos
&#91;edit&#93;       &#160;

Defined in header ` <variant> `

```
template  <     class  ...     Types     >
class     variant  ;

```

(since C++17)

The class template `std::variant` represents a type-safe  union .

An instance of `variant` at any given time either holds a value of one of its alternative types, or in the case of error - no value (this state is hard to achieve, see   valueless_by_exception  ).

As with unions, if a variant holds a value of some object type `T`, the `T` object is  nested within  the `variant` object.

A variant is not permitted to hold references, arrays, or the type ` void `.

A variant is permitted to hold the same type more than once, and to hold differently cv-qualified versions of the same type.

Consistent with the behavior of unions during  aggregate initialization , a default-constructed variant holds a value of its first alternative, unless that alternative is not default-constructible (in which case the variant is not default-constructible either). The helper class   std::monostate   can be used to make such variants default-constructible.

A program that instantiates the definition of `std::variant` with no template arguments is ill-formed. ` std  ::  variant  <  std  ::  monostate  > ` can be used instead.

If a program declares an  explicit  or  partial  specialization of `std::variant`, the program is ill-formed, no diagnostic required.

## Contents

1   Template parameters
2   Member functions

2.1   Observers
2.2   Modifiers
2.3   Visitation

3   Non-member functions
4   Helper classes
5   Helper objects
6   Notes
7   Example
8   Defect reports
9   See also

###  Template parameters

Types

-

the types that may be stored in this variant. All types must meet the   Destructible   requirements (in particular, array types and non-object types are not allowed).

###  Member functions

(constructor)

constructs the `variant` object
(public member function)    &#91;edit&#93;

(destructor)

destroys the `variant`, along with its contained value
(public member function)    &#91;edit&#93;

operator=

assigns a `variant`
(public member function)    &#91;edit&#93;

Observers

index

returns the zero-based index of the alternative held by the `variant`
(public member function)    &#91;edit&#93;

valueless_by_exception

checks if the `variant` is in the invalid state
(public member function)    &#91;edit&#93;

Modifiers

emplace

constructs a value in the `variant`, in place
(public member function)    &#91;edit&#93;

swap

swaps with another `variant`
(public member function)    &#91;edit&#93;

Visitation

visit        (C++26)

calls the provided functor with the argument held by the `variant`
(public member function)    &#91;edit&#93;

###  Non-member functions

visit        (C++17)

calls the provided functor with the arguments held by one or more `variant`s
(function template)    &#91;edit&#93;

holds_alternative        (C++17)

checks if a `variant` currently holds a given type
(function template)    &#91;edit&#93;

get (std::variant)         (C++17)

reads the value of the variant given the index or the type (if the type is unique), throws on error
(function template)    &#91;edit&#93;

get_if        (C++17)

obtains a pointer to the value of a pointed-to `variant` given the index or the type (if unique), returns null on error
(function template)    &#91;edit&#93;

operator==  operator!=  operator<  operator<=  operator>  operator>=  operator<=>        (C++17)    (C++17)    (C++17)    (C++17)    (C++17)    (C++17)    (C++20)

compares `variant` objects as their contained values
(function template)    &#91;edit&#93;

std::swap (std::variant)         (C++17)

specializes the   std::swap   algorithm
(function template)    &#91;edit&#93;

###  Helper classes

monostate        (C++17)

placeholder type for use as the first alternative in a `variant` of non-default-constructible types
(class)    &#91;edit&#93;

bad_variant_access        (C++17)

exception thrown on invalid accesses to the value of a `variant`
(class)    &#91;edit&#93;

variant_size  variant_size_v        (C++17)

obtains the size of the `variant`'s list of alternatives at compile time
(class template)   (variable template)   &#91;edit&#93;

variant_alternative  variant_alternative_t        (C++17)

obtains the type of the alternative specified by its index, at compile time
(class template)   (alias template)   &#91;edit&#93;

std::hash <std::variant>         (C++17)

hash support for   std::variant
(class template specialization)    &#91;edit&#93;

###  Helper objects

variant_npos        (C++17)

index of the `variant` in the invalid state
(constant)    &#91;edit&#93;

###  Notes

Feature-test  macro

Value

Std

Feature

`__cpp_lib_variant`
`201606L`
(C++17)
`std::variant`: a type-safe union

`202102L`
(C++23)
(DR17)
std::visit   for classes derived from `std::variant`

`202106L`
(C++23)
(DR20)
Fully `constexpr` `std::variant`

`202306L`
(C++26)
Member  `visit`

###  Example

Run this code

```
#include     <cassert>
#include     <iostream>
#include     <string>
#include     <variant>

int     main  ()
{
std  ::  variant  <  int  ,     float  >     v  ,     w  ;
v     =     42  ;     // v contains int
int     i     =     std  ::  get  <  int  >  (  v  );
assert  (  42     ==     i  );     // succeeds
w     =     std  ::  get  <  int  >  (  v  );
w     =     std  ::  get  <  0  >  (  v  );     // same effect as the previous line
w     =     v  ;     // same effect as the previous line

//  std::get<double>(v); // error: no double in [int, float]
//  std::get<3>(v);      // error: valid index values are 0 and 1

try
{
std  ::  get  <  float  >  (  w  );     // w contains int, not float: will throw
}
catch     (  const     std  ::  bad_variant_access  &     ex  )
{
std  ::  cout     <<     ex  .  what  ()     <<     &#39;\n&#39;  ;
}

using     namespace     std  ::  literals  ;

std  ::  variant  <  std  ::  string  >     x  (  "abc"  );
// converting constructors work when unambiguous
x     =     "def"  ;     // converting assignment also works when unambiguous

std  ::  variant  <  std  ::  string  ,     void     const  *>     y  (  "abc"  );
// casts to void const* when passed a char const*
assert  (  std  ::  holds_alternative  <  void     const  *>  (  y  ));     // succeeds
y     =     "xyz"  s  ;
assert  (  std  ::  holds_alternative  <  std  ::  string  >  (  y  ));     // succeeds
}

```

Possible output:

```
std::get: wrong index for variant

```

###  Defect reports

The following behavior-changing defect reports were applied retroactively to previously published C++ standards.

DR

Applied to

Behavior as published

Correct behavior

LWG 2901

C++17

specialization of   std::uses_allocator   provided,
but `variant` cannot properly support allocators

specialization removed

LWG 3990

C++17

a program could declare an explicit or
partial specialization of `std::variant`

the program is ill-formed in this
case (no diagnostic required)

LWG 4141

C++17

the requirement for storage
allocation was confusing

the contained object must be
nested within the `variant` object

###  See also

in_place  in_place_type  in_place_index  in_place_t  in_place_type_t  in_place_index_t        (C++17)

in-place construction tag
(tag)   &#91;edit&#93;

optional        (C++17)

a wrapper that may or may not hold an object
(class template)    &#91;edit&#93;

any        (C++17)

objects that hold instances of any   CopyConstructible   type
(class)    &#91;edit&#93;

Category :    Pages using deprecated source tags

Navigation

Support us                      Recent changes                      FAQ                      Offline version

Tools

What links here                      Related changes                      Upload file                      Special pages                      Printable version                      Permanent link                      Page information

In other languages

Español                      日本語                      Русский                      中文
