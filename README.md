Moggy
=====

Moggy is a mock objects framework for the [Groovy](http://groovy.codehaus.org/) programming language. It is inspired by the [mockito mock framework](http://code.google.com/p/mockito/) and uses a similar api providing fluent expectations and verifications.

Moggy makes use of a lot of Groovy features (most of all Closures) and supports both typed and
def'ed mock objects.

## Installation

* _TODO_: Make downloads available


## Usage
###Creating a mock object

Creating a mock object is easy. Statically import de.is24.groovy.moggy.Moggy and call its mock method:

    def mock = mock()
 
The mock object can then be used (i.e. injected into other objects). By default

* all method invocations are caught and simply return null
* all property set expressions set the property
* all property get expression return the property's value, if it has been previously set or null if it hasn't been set.

Thus, the following code works:

    def mock = mock()
 
    mock.foo       // Returns null
    mock.foo = 7   // Ok
    mock.foo       // Now returns 7
 
    mock.bar()     // Returns null
 
###Verifying Method Calls
To verify that a mock's method has been called with a given set of argument values, the class Moggy provides a verify method that almost works like the one you know from Mockito.

    def mock = mock()
 
    mock.bar(7)
 
    verify(mock).bar(7) // Passes, as we have called bar before
 
Verifications fail with an AssertionError when the method has never been called with the given set of arguments (or at all)

    def mock = mock()
    verify(mock).bar(7) // Throws an AssertionError
 
If you want to verify that a given operation has been invoked more than one time, you can provide the expected number of invocations as a second parameter to verfiy:

    def mock = mock()
    mock.bar(7)
    mock.bar(7)

    verify(mock, 2).bar(7)

You can also pass in zero to verify that a method has never been called.

###Defining Behavior
If you expect some method to be invoked on a mock and you want that invocation to do something, you can use when to define that behavior before you actually use the mock. Basically, you can specify a closure to be executed when the method is invoked on the mock. There are convenience methods provided that let you return values or raise exceptions more easily.

####Basic Behavior
 
    def mock = mock()
    when(mock).boom("foo").thenReturn("foo")
 
    mock.boom("foo") // Returns "foo"
    mock.boom()      // Return null; notice that the argument does not match
 
    def mock = mock()
    when(mock).boom("foo").thenThrow(new IllegalArgumentException("foo"))
 
    mock.boom("foo") // Throws an IllegalArgumentException
 
    def mock = mock()
    when(mock).boom().thenDo { ... }
 
    mock.boom()
 
####Specifying different Behavior for successive Invocations
You can chain any of these then... methods to specify different behavior for different invocations with the same arguments:

    def mock = mock()
    when(mock).boom("foo").thenReturn("foo").thenReturn("bar").thenThrow(new Exception("Caboom"))
 
    mock.boom("foo") // Returns "foo"
    mock.boom("foo") // Returns "bar"
    mock.boom("foo") // Throws the exception

Defining different Behavior for Invocations with different Arguments
Of course, different argument values work as expected

    def mock = mock()
    when(mock).boom("foo").thenReturn("foo")
    when(mock).boom("bar").thenReturn("bar")
 
    mock.boom("foo") // Returns "foo"
    mock.boom("bar") // Returns "bar"

####Matching Arguments using Matchers
It is also possible to use "matchers" to capture a range of argument values. The most commonly used matcher is the "any" matcher that
matches any value.

    def mock = mock()
    when(mock).boom(any()).thenReturn("foo")
 
    mock.boom("foo") // Returns "foo"
    mock.boom("bar") // Returns "foo"
 
There are some more matchers:

* eq(value) - only matches the given value. This is the default used when you specify a bare argument value (such as "foo" or 8)
* nullValue() - matches any value that is null
* notNull() - matches any value that is not null (suprisingly)
* contains(value) - matches arguments that are strings and contain the given value
* startsWith(value) - matches arguments that are strings and start with the given value
* endsWith(value) - matches arguments that are strings and end with the given value
* matches(value) - matches arguments that are strings and match the given regular expression value


####Writing custom Matchers
A matcher is nothing special. Its a plain old Groovy class that implements the interface de.is24.groovy.moggy.behavior.Matcher. You can
write your own classes implementing the Matcher interface and use them in when statements.
