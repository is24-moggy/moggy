package de.is24.groovy.moggy

import static junit.framework.Assert.*
import groovy.util.GroovyTestCase
import static de.is24.groovy.moggy.Moggy.any
import static de.is24.groovy.moggy.Moggy.notNull

class MoggyTest extends GroovyTestCase {

  void testMockShouldReturnNullWhenNoBehaviorHasBeenDefined() {
    def mock = Moggy.mock()

    assertNull mock.boom("foo", "bar", 99)
  }

  void testMockShouldReturnValueWhenBehaviorHasBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo", "bar", 99).thenReturn("result")

    assertEquals "result", mock.boom("foo", "bar", 99)
  }

  void testMockShouldReturnDifferentValuesForDifferentArgumentsWhenDifferentBehaviorsHaveBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenReturn("foo")
    Moggy.when(mock).boom("bar").thenReturn("bar")

    assertEquals "foo", mock.boom("foo")
    assertEquals "bar", mock.boom("bar")
  }

  void testMockShouldReturnDifferentValuesWhenDifferentBehaviorsHaveBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenReturn("foo").thenReturn("bar")

    assertEquals "foo", mock.boom("foo")
    assertEquals "bar", mock.boom("foo")
  }

  void testMockShouldThrowExceptionWhenBehaviorIsBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenThrow(new IllegalArgumentException("foo"))

    try {
      mock.boom("foo")
      fail "Excpected illegal argument exception"
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }

  void testShouldVerifyMockCall() {
    def mock = Moggy.mock()

    mock.boom("foo")

    Moggy.verify(mock).boom("foo")
  }

  void testShouldVerifyMockCallWithAnyMatcher() {
    def mock = Moggy.mock()

    mock.boom("bar", "foo", null)

    Moggy.verify(mock).boom(any(), "foo", any())
    Moggy.verify(mock).boom("bar", any(), any())
    Moggy.verify(mock).boom(any(), any(), any())
  }

  void testShouldVerifyWithClosureMatcher() {
    def mock = Moggy.mock()

    mock.boom("test")

    Moggy.verify(mock).boom(Moggy.closureMatcher { it instanceof String })
  }

  void testVerifyShouldFailWithClosureMatcher() {
    def mock = Moggy.mock()
    mock.boom("test")
    try {
      Moggy.verify(mock).boom(Moggy.closureMatcher { it instanceof Integer })
      fail "AssertionError expected"
    }
    catch (AssertionError e) {
      // excepted
    }
  }


  void testShouldThrowExceptionWhenVerifyingMockCallAndArgumentsDoNotMath() {
    def mock = Moggy.mock()

    mock.boom("foo")

    try {
      Moggy.verify(mock).boom("bar")
      fail "AssertionError expected"
    } catch (AssertionError e) {
      // excepted
    }
  }

  void testShouldThrowExceptionWhenVerifyingMockCallAndExpectedMethodHasNotBeenCalled() {
    def mock = Moggy.mock()

    mock.boom("foo")

    try {
      Moggy.verify(mock).foo("foo")
      fail "AssertionError expected"
    } catch (AssertionError e) {
      // excepted
    }
  }

  void testShouldPassParametersToBehaviorClosures() {
    def mock = Moggy.mock()

    Moggy.when(mock).foo(7).thenDo { param -> assert [7] == param}

    mock.foo(7)
  }

  void testShouldDefinePropertyDynamically() {
    def mock = Moggy.mock()

    mock.foo = 7

    assertEquals 7, mock.foo
  }

  void testShouldReturnNullForUndefinedProperty() {
    def mock = Moggy.mock()

    assertNull mock.foo
  }

  void testShouldExecuteClosureWhenDereferencingPropertyAndPropertyHasBeenSetBefore() {
    def mock = Moggy.mock()

    mock.foo = { throw new RuntimeException("Caboom") }

    shouldFail(RuntimeException.class) { mock.foo }
  }

  void testShouldFailWhenNumberOfInvocationsDoesNotMatchExpectation () {
    def mock = Moggy.mock()

    mock.foo()

    shouldFail(AssertionError.class) {
      Moggy.verify(mock, 2).foo()
    }
  }

  void testShouldVerifyThatNumberOfInvocationsMatchExpectation () {
    def mock = Moggy.mock()

    mock.foo()
    mock.foo()

    Moggy.verify(mock, 2).foo()
  }

  void testShouldVerifyNoInvocation () {
    def mock = Moggy.mock()

    Moggy.verify(mock, 0).foo()
  }

  void testShouldPickCorrectBehaviorWhenDefiningMultipleExpectations () {
    def mock = Moggy.mock()

    Moggy.when(mock).foo(Moggy.anyValue(), 1, 2).thenReturn(2)
    Moggy.when(mock).foo(Moggy.anyValue(), 1, 5).thenReturn(5)

    assertEquals 2, mock.foo(0, 1, 2)
    assertEquals 5, mock.foo(0, 1, 5)
  }
}
