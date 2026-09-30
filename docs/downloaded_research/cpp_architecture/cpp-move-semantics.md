---
source_url: https://en.cppreference.com/w/cpp/language/move_constructor
title: cppreference: Move Constructors and Rvalue References
downloaded_at_utc: 2026-09-30T07:56:28.985697+00:00
http_status: 200
content_sha256: d4b41b9d9d50fd05cb53f2042c3ede3f4f515b3466171f30959ae6f89bd718e9
category: cpp_architecture
---

# cppreference: Move Constructors and Rvalue References

**Официальный первоисточник:** [https://en.cppreference.com/w/cpp/language/move_constructor](https://en.cppreference.com/w/cpp/language/move_constructor)  
**Статус загрузки:** HTTP 200 OK  
**Хэш содержимого (SHA-256):** `d4b41b9d9d50fd05cb53f2042c3ede3f4f515b3466171f30959ae6f89bd718e9`  

---


Move constructors - cppreference.com

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

# Move constructors

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

&#91;edit&#93;       &#160;    Classes

General
Overview
`class`/`struct` types
`union` types
Injected-class-name
Members
Data members
Static members
The `this` pointer
Nested classes
Member templates
Bit-fields
`using`-declarations
Member functions
Member access specifiers
Constructors and member initializer lists
Default member initializer   (C++11)
`friend` specifier
`explicit` specifier
Converting constructor
Special member functions
Default constructor
Copy constructor
Move constructor   (C++11)
Copy assignment operator
Move assignment operator   (C++11)
Destructor
Inheritance
Base and derived classes
Empty base optimization (EBO)
Virtual member functions
Pure virtual functions and abstract classes
`override` specifier   (C++11)
`final` specifier   (C++11)

&#91;edit&#93;       &#160;

A move constructor is a  constructor  which can be called with an argument of the same class type and copies the content of the argument, possibly mutating the argument.

## Contents

1   Syntax
2   Explanation
3   Implicitly-declared move constructor
4   Implicitly-defined move constructor
5   Deleted move constructor
6   Trivial move constructor
7   Eligible move constructor
8   Notes
9   Example
10   Defect reports
11   See also

###  Syntax

class-name &#x200a;&#xfeff;` ( ` parameter-list &#x200a;&#xfeff;` ); `

(1)

class-name &#x200a;&#xfeff;` ( ` parameter-list &#x200a;&#xfeff;` ) `  function-body

(2)

class-name &#x200a;&#xfeff;` ( ` single-parameter-list &#x200a;&#xfeff;` ) = default; `

(3)

class-name &#x200a;&#xfeff;` ( ` parameter-list &#x200a;&#xfeff;` ) = delete; `

(4)

class-name &#x200a;&#xfeff;` :: ` class-name &#x200a;&#xfeff;` ( ` parameter-list &#x200a;&#xfeff;` ) `  function-body

(5)

class-name &#x200a;&#xfeff;` :: ` class-name &#x200a;&#xfeff;` ( ` single-parameter-list &#x200a;&#xfeff;` ) = default; `

(6)

class-name

-

the class whose move constructor is being declared

parameter-list

-

a non-empty  parameter list  satisfying all following conditions:
given the class type as `T`, the first parameter is of type ` T  && `, ` const     T  && `, ` volatile     T  && ` or ` const     volatile     T  && `, and
either there are no other parameters, or all other parameters have  default arguments

single-parameter-list

-

a  parameter list  of only one parameter, which is of type ` T  && `, ` const     T  && `, ` volatile     T  && ` or ` const     volatile     T  && ` and does not have a default argument

function-body

-

the  function body  of the move constructor

###  Explanation

1)  Declaration of a move constructor inside of class definition.
2-4)  Definition of a move constructor inside of class definition.
3)  The move constructor is explicitly-defaulted.
4)  The move constructor is deleted.
5,6)  Definition of a move constructor outside of class definition (the class must contain a declaration  (1) ).
6)  The move constructor is explicitly-defaulted.

```
struct     X
{
X  (  X  &&     other  );     // move constructor
//  X(X other);   // Error: incorrect parameter type
};

union     Y
{
Y  (  Y  &&     other  ,     int     num     =     1  );     // move constructor with multiple parameters
//  Y(Y&& other, int num);     // Error: `num` has no default argument
};

```

The move constructor is typically called when an object is  initialized  (by  direct-initialization  or  copy-initialization ) from    rvalue  (xvalue or prvalue)   (until C++17)     xvalue   (since C++17)    of the same type, including

initialization:  ` T     a     =     std  ::  move  (  b  ); `  or  ` T     a  (  std  ::  move  (  b  )); ` , where  ` b `  is of type `T`;
function argument passing:  ` f  (  std  ::  move  (  a  )); ` , where  ` a `  is of type `T` and  ` f `  is  ` void     f  (  T     t  ) ` ;
function return:  ` return     a  ; `  inside a function such as  ` T     f  () ` , where  ` a `  is of type `T` which has a move constructor.

When the initializer is a prvalue, the move constructor call is   often optimized out   (until C++17)     never made   (since C++17)   , see  copy elision .

Move constructors typically transfer the resources held by the argument (e.g. pointers to dynamically-allocated objects, file descriptors, TCP sockets, thread handles, etc.) rather than make copies of them, and leave the argument in some valid but otherwise indeterminate state. Since move constructor doesn’t change the lifetime of the argument, the destructor will typically be called on the argument at a later point. For example, moving from a   std::string   or from a   std::vector   may result in the argument being left empty. For some types, such as   std::unique_ptr  , the moved-from state is fully specified.

###  Implicitly-declared move constructor

If no user-defined move constructors are provided for a class type, and all of the following is true:

there are no user-declared  copy constructors ;
there are no user-declared  copy assignment operators ;
there are no user-declared  move assignment operators ;
there is no user-declared  destructor .

Then the compiler will declare a move constructor as a non- explicit  ` inline     public ` member of its class with the signature  ` T  ::  T  (  T  &&  ) ` .

A class can have multiple move constructors, e.g. both  ` T  ::  T  (  const     T  &&  ) `  and  ` T  ::  T  (  T  &&  ) ` . If some user-defined move constructors are present, the user may still force the generation of the implicitly declared move constructor with the keyword ` default `.

The implicitly-declared (or defaulted on its first declaration) move constructor has an exception specification as described in    dynamic exception specification    (until C++17)      noexcept specification    (since C++17)   .

###  Implicitly-defined move constructor

If the implicitly-declared move constructor is neither deleted nor trivial, it is defined (that is, a function body is generated and compiled) by the compiler if  ODR-use  or  needed for constant evaluation . For union types, the implicitly-defined move constructor copies the object representation (as by   std::memmove  ). For non-union class types, the move constructor performs full member-wise move of the object's direct base subobjects and member subobjects, in their initialization order, using direct initialization with an  xvalue  argument. For each non-static data member of a reference type, the move constructor binds the reference to the same object or function to which the source reference is bound.

If this satisfies the requirements of a    `constexpr` constructor    (until C++23)      `constexpr` function    (since C++23)   , the generated move constructor is ` constexpr `.

###  Deleted move constructor

The implicitly-declared or explicitly-defaulted move constructor for class `T` is defined as deleted if `T` has a  potentially constructed subobject  of class type `M` (or possibly multi-dimensional array thereof) such that

`M` has a destructor that is deleted or inaccessible from the copy constructor, or
the overload resolution as applied to find `M`'s move constructor
does not result in a usable candidate, or
in the case of the subobject being a  variant member , selects a non-trivial function.

Such a constructor is ignored by  overload resolution  (otherwise it would prevent copy-initialization from rvalue).

###  Trivial move constructor

The move constructor for class `T` is trivial if all of the following is true:

it is not user-provided (meaning, it is implicitly-defined or defaulted);
`T` has no virtual member functions;
`T` has no virtual base classes;
the move constructor selected for every direct base of `T` is trivial;
the move constructor selected for every non-static class type (or array of class type) member of `T` is trivial.

A trivial move constructor is a constructor that performs the same action as the trivial copy constructor, that is, makes a copy of the object representation as if by   std::memmove  . All data types compatible with the C language are trivially movable.

###  Eligible move constructor

A move constructor is eligible if it is not deleted.

(until C++20)

A move constructor is eligible if all following conditions are satisfied:

It is not deleted.
Its  associated constraints  (if any) are satisfied.
No move constructor whose associated constraints are satisfied is  more constrained .

(since C++20)

Triviality of eligible move constructors determines whether the class is an  implicit-lifetime type , and whether the class is a  trivially copyable type .

###  Notes

To make the  strong exception guarantee  possible, user-defined move constructors should not throw exceptions. For example,   std::vector   relies on   std::move_if_noexcept   to choose between move and copy when the elements need to be relocated.

If both copy and move constructors are provided and no other constructors are viable, overload resolution selects the move constructor if the argument is an  rvalue  of the same type (an  xvalue  such as the result of  std::move    or a  prvalue  such as a nameless temporary   (until C++17)   ), and selects the copy constructor if the argument is an  lvalue  (named object or a function/operator returning lvalue reference). If only the copy constructor is provided, all argument categories select it (as long as it takes a reference to const, since rvalues can bind to const references), which makes copying the fallback for moving, when moving is unavailable.

###  Example

Run this code

```
#include     <iomanip>
#include     <iostream>
#include     <string>
#include     <utility>

struct     A
{
std  ::  string     s  ;
int     k  ;

A  ()     :     s  (  "test"  ),     k  (  -1  )     {}
A  (  const     A  &     o  )     :     s  (  o  .  s  ),     k  (  o  .  k  )     {     std  ::  cout     <<     "move failed!  \n  "  ;     }
A  (  A  &&     o  )     noexcept     :
s  (  std  ::  move  (  o  .  s  )),           // explicit move of a member of class type
k  (  std  ::  exchange  (  o  .  k  ,     0  ))     // explicit move of a member of non-class type
{}
};

A     f  (  A     a  )
{
return     a  ;
}

struct     B     :     A
{
std  ::  string     s2  ;
int     n  ;
// implicit move constructor B::(B&&)
// calls A&#39;s move constructor
// calls s2&#39;s move constructor
// and makes a bitwise copy of n
};

struct     C     :     B
{
~  C  ()     {}     // destructor prevents implicit move constructor C::(C&&)
};

struct     D     :     B
{
D  ()     {}
~  D  ()     {}               // destructor would prevent implicit move constructor D::(D&&)
D  (  D  &&  )     =     default  ;     // forces a move constructor anyway
};

int     main  ()
{
std  ::  cout     <<     "Trying to move A  \n  "  ;
A     a1     =     f  (  A  ());     // return by value move-constructs the target
// from the function parameter

std  ::  cout     <<     "Before move, a1.s = "     <<     std  ::  quoted  (  a1  .  s  )
<<     " a1.k = "     <<     a1  .  k     <<     &#39;\n&#39;  ;

A     a2     =     std  ::  move  (  a1  );     // move-constructs from xvalue
std  ::  cout     <<     "After move, a1.s = "     <<     std  ::  quoted  (  a1  .  s  )
<<     " a1.k = "     <<     a1  .  k     <<     &#39;\n&#39;  ;

std  ::  cout     <<     "  \n  Trying to move B  \n  "  ;
B     b1  ;

std  ::  cout     <<     "Before move, b1.s = "     <<     std  ::  quoted  (  b1  .  s  )     <<     "  \n  "  ;

B     b2     =     std  ::  move  (  b1  );     // calls implicit move constructor
std  ::  cout     <<     "After move, b1.s = "     <<     std  ::  quoted  (  b1  .  s  )     <<     "  \n  "  ;

std  ::  cout     <<     "  \n  Trying to move C  \n  "  ;
C     c1  ;
C     c2     =     std  ::  move  (  c1  );     // calls copy constructor

std  ::  cout     <<     "  \n  Trying to move D  \n  "  ;
D     d1  ;
D     d2     =     std  ::  move  (  d1  );
}

```

Output:

```
Trying to move A
Before move, a1.s = "test" a1.k = -1
After move, a1.s = "" a1.k = 0

Trying to move B
Before move, b1.s = "test"
After move, b1.s = ""

Trying to move C
move failed!

Trying to move D

```

###  Defect reports

The following behavior-changing defect reports were applied retroactively to previously published C++ standards.

DR

Applied to

Behavior as published

Correct behavior

CWG 1353

C++11

the conditions where defaulted move constructors are
defined as deleted did not consider multi-dimensional array types

consider these types

CWG 1402

C++11

a defaulted move constructor that would call
a non-trivial copy constructor was defined as
deleted; a defaulted move constructor that is
deleted still participated in overload resolution

allows call to such copy
constructor; made ignored
in overload resolution

CWG 1491

C++11

a defaulted move constructor of a class with a non-static data
member of rvalue reference type was defined as deleted

not deleted in this case

CWG 2094

C++11

a volatile subobject made a defaulted
move constructor non-trivial ( CWG issue 496 )

triviality not affected

CWG 2595

C++20

a move constructor was not eligible if there is
another move constructor which is more constrained
but does not satisfy its associated constraints

it can be eligible in this case

###  See also

converting constructor
copy assignment
copy constructor
copy elision
default constructor
destructor
explicit
initialization
aggregate initialization
constant initialization
copy initialization
default initialization
direct initialization
initializer list
list initialization
reference initialization
value initialization
zero initialization
move assignment
new

Categories :    Pages using deprecated source tags    Pages using deprecated enclose attributes

Navigation

Support us                      Recent changes                      FAQ                      Offline version

Tools

What links here                      Related changes                      Upload file                      Special pages                      Printable version                      Permanent link                      Page information

In other languages

Deutsch                      Español                      Français                      Italiano                      日本語                      Português                      Русский                      中文
