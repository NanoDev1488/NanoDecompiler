---
source_url: https://en.cppreference.com/w/cpp/memory/unique_ptr
title: cppreference: std::unique_ptr and AST Node Ownership
downloaded_at_utc: 2026-09-30T07:56:31.160310+00:00
http_status: 200
content_sha256: da6fe410766097c079fc64d454f0c03063a47a623fcb1996e7fe8270d7917148
category: cpp_architecture
---

# cppreference: std::unique_ptr and AST Node Ownership

**Официальный первоисточник:** [https://en.cppreference.com/w/cpp/memory/unique_ptr](https://en.cppreference.com/w/cpp/memory/unique_ptr)  
**Статус загрузки:** HTTP 200 OK  
**Хэш содержимого (SHA-256):** `da6fe410766097c079fc64d454f0c03063a47a623fcb1996e7fe8270d7917148`  

---


std::unique_ptr - cppreference.com

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

#  std:: unique_ptr

From cppreference.com

<   cpp   |   memory

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
&#91;edit&#93;       &#160;   Memory management library

Allocators

allocator
allocator_traits        (C++11)
allocation_result        (C++23)
scoped_allocator_adaptor        (C++11)

allocator_arg        (C++11)
uses_allocator        (C++11)
uses_allocator_construction_args        (C++20)
make_obj_using_allocator        (C++20)

pmr::polymorphic_allocator        (C++17)
uninitialized_construct_using_allocator        (C++20)
Memory resources

pmr::memory_resource        (C++17)
pmr::get_default_resource        (C++17) &#160;&#160;&#160;&#160;
pmr::set_default_resource        (C++17)
pmr::new_delete_resource        (C++17)
pmr::pool_options        (C++17)

pmr::null_memory_resource        (C++17)
pmr::synchronized_pool_resource        (C++17)
pmr::unsynchronized_pool_resource        (C++17) &#160;&#160;&#160;&#160;
pmr::monotonic_buffer_resource        (C++17)

Explicit lifetime management
start_lifetime_as        (C++23)
start_lifetime_as_array        (C++23)

Types for composite class design
indirect        (C++26)
polymorphic        (C++26)

Miscellaneous
pointer_traits        (C++11)
to_address        (C++20)
addressof        (C++11)
align        (C++11)
assume_aligned        (C++20)
is_sufficiently_aligned        (C++26)

C Library

malloc       &#160;&#160;&#160;&#160;
free

calloc

realloc

aligned_alloc        (C++17)

free_sized        (C++26)
free_aligned_sized        (C++26)
memalignment        (C++26)

Uninitialized storage   ( until C++20* )

raw_storage_iterator
get_temporary_buffer

return_temporary_buffer

Garbage collector support   (until C++23)

declare_reachable        (C++11)
declare_no_pointers        (C++11)
pointer_safety        (C++11)

undeclare_reachable        (C++11)
undeclare_no_pointers        (C++11)
get_pointer_safety        (C++11)

Specialized `<memory>`
algorithms
Low level memory
management
operator new  operator new[]
operator delete  operator delete[]
nothrow
nothrow_t
new_handler
set_new_handler
get_new_handler        (C++11)
bad_alloc
bad_array_new_length        (C++11)
align_val_t        (C++17)
destroying_delete_t        (C++20)
launder        (C++17)
Smart pointers
unique_ptr        (C++11)
shared_ptr        (C++11)
weak_ptr        (C++11)
auto_ptr        ( until C++17* )
owner_less        (C++11)
owner_less<void>        (C++17)
owner_hash        (C++26)
owner_equal        (C++26)
enable_shared_from_this        (C++11)
bad_weak_ptr        (C++11)
default_delete        (C++11)
out_ptr_t        (C++23)
inout_ptr_t        (C++23)

&#91;edit&#93;       &#160;    std::unique_ptr
Member functions
unique_ptr::unique_ptr
unique_ptr::~unique_ptr
unique_ptr::operator=
Modifiers
unique_ptr::release
unique_ptr::reset
unique_ptr::swap
Observers
unique_ptr::get
unique_ptr::get_deleter
unique_ptr::operator bool
unique_ptr::operator*  unique_ptr::operator->
unique_ptr::operator[]
Non-member functions
make_unique  make_unique_for_overwrite        (C++14)    (C++20)
operator==  operator!=  operator<  operator>  operator<=  operator>=  operator<=>          (until C++20)            (C++20)
operator<<        (C++20)
swap (std::unique_ptr)
Helper classes
hash <std::unique_ptr>
&#91;edit&#93;       &#160;

Defined in header ` <memory> `

```
template  <
class     T  ,
class     Deleter     =     std  ::  default_delete  <  T  >
>     class     unique_ptr  ;

```

(1)
(since C++11)

```
template     <
class     T  ,
class     Deleter
>     class     unique_ptr  <  T  [],     Deleter  >  ;

```

(2)
(since C++11)

`std::unique_ptr` is a smart pointer that owns (is responsible for) and manages another object via a pointer and subsequently disposes of that object when the `unique_ptr` goes out of scope.

The object is disposed of, using the associated deleter, when either of the following happens:

the managing `unique_ptr` object is destroyed.
the managing `unique_ptr` object is assigned another pointer via   operator=   or   reset()  .

The object is disposed of, using a potentially user-supplied deleter, by calling  ` get_deleter  ()(  ptr  ) ` . The default deleter (`std::default_delete`) uses the ` delete ` operator, which destroys the object and deallocates the memory.

A `unique_ptr` may alternatively own no object, in which case it is described as  empty .

There are two versions of `unique_ptr`:

Manages a single object (e.g., allocated with ` new `).
Manages a dynamically-allocated array of objects (e.g., allocated with ` new  [] `).

The class satisfies the requirements of   MoveConstructible   and   MoveAssignable  , but of neither   CopyConstructible   nor   CopyAssignable  .

If `T*` was not a valid type (e.g., `T` is a reference type), a program that instantiates the definition of ` std  ::  unique_ptr  <  T  ,     Deleter  > ` is ill-formed.

Type requirements

-  `Deleter` must be   FunctionObject   or lvalue reference to a   FunctionObject   or lvalue reference to function, callable with an argument of type ` unique_ptr  <  T  ,     Deleter  >::  pointer `.

## Contents

1   Notes
2   Nested types
3   Member functions

3.1   Modifiers
3.2   Observers
3.3   Single-object version, unique_ptr<T>
3.4   Array version, unique_ptr<T[]>

4   Non-member functions
5   Helper classes
6   Example
7   Defect reports
8   See also

###  Notes

Only non-const `unique_ptr` can transfer the ownership of the managed object to another `unique_ptr`. If an object's lifetime is managed by a ` const     std  ::  unique_ptr `, it is limited to the scope in which the pointer was created.

`unique_ptr` is commonly used to manage the lifetime of objects, including:

providing exception safety to classes and functions that handle objects with dynamic lifetime, by guaranteeing deletion on both normal exit and exit through exception.
passing ownership of uniquely-owned objects with dynamic lifetime into functions.
acquiring ownership of uniquely-owned objects with dynamic lifetime from functions.
as the element type in move-aware containers, such as   std::vector  , which hold pointers to dynamically-allocated objects (e.g. if polymorphic behavior is desired).

`unique_ptr` may be constructed for an  incomplete type  `T`, such as to facilitate the use as a handle in the  pImpl idiom . If the default deleter is used, `T` must be complete at the point in code where the deleter is invoked, which happens in the destructor, move assignment operator, and `reset` member function of `unique_ptr`. (In contrast,   std::shared_ptr   cannot be constructed from a raw pointer to incomplete type, but can be destroyed where `T` is incomplete). Note that if `T` is a class template specialization, use of `unique_ptr` as an operand, e.g.  ` !  p `  requires `T`'s parameters to be complete due to  ADL .

If `T` is a  derived class  of some base `B`, then ` unique_ptr  <  T  > ` is  implicitly convertible  to ` unique_ptr  <  B  > `. The default deleter of the resulting ` unique_ptr  <  B  > ` will use   operator delete   for `B`, leading to  undefined behavior  unless the destructor of `B` is  virtual . Note that   std::shared_ptr   behaves differently: ` std  ::  shared_ptr  <  B  > ` will use the   operator delete   for the type `T` and the owned object will be deleted correctly even if the destructor of `B` is not  virtual .

Unlike   std::shared_ptr  , `unique_ptr` may manage an object through any custom handle type that satisfies   NullablePointer  . This allows, for example, managing objects located in shared memory, by supplying a `Deleter` that defines `typedef  boost::offset_ptr  pointer;` or another  fancy pointer .

Feature-test  macro
Value
Std
Feature

`__cpp_lib_constexpr_memory`
`202202L`
(C++23)
` constexpr `  `std::unique_ptr`

###  Nested types

Type

Definition

` pointer `

` std  ::  remove_reference  <  Deleter  >::  type  ::  pointer ` if that type exists, otherwise `T*`. Must satisfy   NullablePointer

` element_type `

`T`, the type of the object managed by this `unique_ptr`

` deleter_type `

`Deleter`, the function object or lvalue reference to function or to function object, to be called from the destructor

###  Member functions

(constructor)

constructs a new `unique_ptr`
(public member function)    &#91;edit&#93;

(destructor)

destructs the managed object if such is present
(public member function)    &#91;edit&#93;

operator=

assigns the `unique_ptr`
(public member function)    &#91;edit&#93;

Modifiers

release

returns a pointer to the managed object and releases the ownership
(public member function)    &#91;edit&#93;

reset

replaces the managed object
(public member function)    &#91;edit&#93;

swap

swaps the managed objects
(public member function)    &#91;edit&#93;

Observers

get

returns a pointer to the managed object
(public member function)    &#91;edit&#93;

get_deleter

returns the deleter that is used for destruction of the managed object
(public member function)    &#91;edit&#93;

operator bool

checks if there is an associated managed object
(public member function)    &#91;edit&#93;

Single-object version, `unique_ptr<T>`

operator*  operator->

dereferences pointer to the managed object
(public member function)    &#91;edit&#93;

Array version, `unique_ptr<T[]>`

operator[]

provides indexed access to the managed array
(public member function)    &#91;edit&#93;

###  Non-member functions

make_unique  make_unique_for_overwrite        (C++14)    (C++20)

creates a unique pointer that manages a new object
(function template)    &#91;edit&#93;

operator==  operator!=  operator<  operator<=  operator>  operator>=  operator<=>          (removed in C++20)            (C++20)

compares to another `unique_ptr` or with  ` nullptr `
(function template)    &#91;edit&#93;

operator<< (std::unique_ptr)         (C++20)

outputs the value of the managed pointer to an output stream
(function template)    &#91;edit&#93;

std::swap (std::unique_ptr)         (C++11)

specializes the   std::swap   algorithm
(function template)    &#91;edit&#93;

###  Helper classes

std::hash <std::unique_ptr>         (C++11)

hash support for   std::unique_ptr
(class template specialization)    &#91;edit&#93;

###  Example

Run this code

```
#include     <cassert>
#include     <cstdio>
#include     <fstream>
#include     <iostream>
#include     <locale>
#include     <memory>
#include     <stdexcept>

// helper class for runtime polymorphism demo below
struct     B
{
virtual     ~  B  ()     =     default  ;

virtual     void     bar  ()     {     std  ::  cout     <<     "B::bar  \n  "  ;     }
};

struct     D     :     B
{
D  ()     {     std  ::  cout     <<     "D::D  \n  "  ;     }
~  D  ()     {     std  ::  cout     <<     "D::~D  \n  "  ;     }

void     bar  ()     override     {     std  ::  cout     <<     "D::bar  \n  "  ;     }
};

// a function consuming a unique_ptr can take it by value or by rvalue reference
std  ::  unique_ptr  <  D  >     pass_through  (  std  ::  unique_ptr  <  D  >     p  )
{
p  ->  bar  ();
return     p  ;
}

// helper function for the custom deleter demo below
void     close_file  (  std  ::  FILE  *     fp  )
{
std  ::  fclose  (  fp  );
}

// unique_ptr-based linked list demo
struct     List
{
struct     Node
{
int     data  ;
std  ::  unique_ptr  <  Node  >     next  ;
};

std  ::  unique_ptr  <  Node  >     head  ;

~  List  ()
{
// destroy list nodes sequentially in a loop, the default destructor
// would have invoked its “next”&#39;s destructor recursively, which would
// cause stack overflow for sufficiently large lists.
while     (  head  )
{
auto     next     =     std  ::  move  (  head  ->  next  );
head     =     std  ::  move  (  next  );
}
}

void     push  (  int     data  )
{
head     =     std  ::  unique_ptr  <  Node  >  (  new     Node  {  data  ,     std  ::  move  (  head  )});
}
};

int     main  ()
{
std  ::  cout     <<     "1) Unique ownership semantics demo  \n  "  ;
{
// Create a (uniquely owned) resource
std  ::  unique_ptr  <  D  >     p     =     std  ::  make_unique  <  D  >  ();

// Transfer ownership to “pass_through”,
// which in turn transfers ownership back through the return value
std  ::  unique_ptr  <  D  >     q     =     pass_through  (  std  ::  move  (  p  ));

// “p” is now in a moved-from &#39;empty&#39; state, equal to nullptr
assert  (  !  p  );
}

std  ::  cout     <<     "  \n  "     "2) Runtime polymorphism demo  \n  "  ;
{
// Create a derived resource and point to it via base type
std  ::  unique_ptr  <  B  >     p     =     std  ::  make_unique  <  D  >  ();

// Dynamic dispatch works as expected
p  ->  bar  ();
}

std  ::  cout     <<     "  \n  "     "3) Custom deleter demo  \n  "  ;
std  ::  ofstream  (  "demo.txt"  )     <<     &#39;x&#39;  ;     // prepare the file to read
{
using     unique_file_t     =     std  ::  unique_ptr  <  std  ::  FILE  ,     decltype  (  &  close_file  )  >  ;
unique_file_t     fp  (  std  ::  fopen  (  "demo.txt"  ,     "r"  ),     &  close_file  );
if     (  fp  )
std  ::  cout     <<     char  (  std  ::  fgetc  (  fp  .  get  ()))     <<     &#39;\n&#39;  ;
}     // “close_file()” called here (if “fp” is not null)

std  ::  cout     <<     "  \n  "     "4) Custom lambda expression deleter and exception safety demo  \n  "  ;
try
{
std  ::  unique_ptr  <  D  ,     void  (  *  )(  D  *  )  >     p  (  new     D  ,     [](  D  *     ptr  )
{
std  ::  cout     <<     "destroying from a custom deleter...  \n  "  ;
delete     ptr  ;
});

throw     std  ::  runtime_error  (  ""  );     // “p” would leak here if it were a plain pointer
}
catch     (  const     std  ::  exception  &  )
{
std  ::  cout     <<     "Caught exception  \n  "  ;
}

std  ::  cout     <<     "  \n  "     "5) Array form of unique_ptr demo  \n  "  ;
{
std  ::  unique_ptr  <  D  []  >     p  (  new     D  [  3  ]);
}     // “D::~D()” is called 3 times

std  ::  cout     <<     "  \n  "     "6) Linked list demo  \n  "  ;
{
List     wall  ;
const     int     enough  {  1&#39;000&#39;000  };
for     (  int     beer     =     0  ;     beer     !=     enough  ;     ++  beer  )
wall  .  push  (  beer  );

std  ::  cout  .  imbue  (  std  ::  locale  (  "en_US.UTF-8"  ));
std  ::  cout     <<     enough     <<     " bottles of beer on the wall...  \n  "  ;
}     // destroys all the beers
}

```

Possible output:

```
1) Unique ownership semantics demo
D::D
D::bar
D::~D

2) Runtime polymorphism demo
D::D
D::bar
D::~D

3) Custom deleter demo
x

4) Custom lambda-expression deleter and exception safety demo
D::D
destroying from a custom deleter...
D::~D
Caught exception

5) Array form of unique_ptr demo
D::D
D::D
D::D
D::~D
D::~D
D::~D

6) Linked list demo
1,000,000 bottles of beer on the wall...

```

###  Defect reports

The following behavior-changing defect reports were applied retroactively to previously published C++ standards.

DR

Applied to

Behavior as published

Correct behavior

LWG 4144

C++11

`T*` was not required to form a valid type

required

###  See also

shared_ptr        (C++11)

smart pointer with shared object ownership semantics
(class template)    &#91;edit&#93;

weak_ptr        (C++11)

weak reference to an object managed by   std::shared_ptr
(class template)    &#91;edit&#93;

indirect        (C++26)

a wrapper containing dynamically-allocated object with value-like semantics
(class template)    &#91;edit&#93;

any        (C++17)

objects that hold instances of any   CopyConstructible   type
(class)    &#91;edit&#93;

Categories :    Pages using deprecated source tags    Pages using deprecated enclose attributes

Navigation

Support us                      Recent changes                      FAQ                      Offline version

Tools

What links here                      Related changes                      Upload file                      Special pages                      Printable version                      Permanent link                      Page information

In other languages

Deutsch                      Español                      Français                      Italiano                      日本語                      Português                      Русский                      中文
