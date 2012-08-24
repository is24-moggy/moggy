package de.is24.groovy.moggy

import org.junit.Test

import static de.is24.groovy.moggy.AssertionUtils.shouldThrow
import static de.is24.groovy.moggy.Moggy.anyValue
import static org.junit.Assert.fail

class MoggyAcceptanceTest {

  @Test
  void mockShouldReturnNullWhenNoBehaviorHasBeenDefined() {
    def mock = Moggy.mock()

    assert null == mock.boom("foo", "bar", 99)
  }

  @Test
  void mockShouldReturnValueWhenBehaviorHasBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo", "bar", 99).thenReturn("result")

    assert "result" == mock.boom("foo", "bar", 99)
  }

  @Test
  void mockShouldReturnDifferentValuesForDifferentArgumentsWhenDifferentBehaviorsHaveBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenReturn("foo")
    Moggy.when(mock).boom("bar").thenReturn("bar")

    assert "foo" == mock.boom("foo")
    assert "bar" == mock.boom("bar")
  }

  @Test
  void mockShouldReturnDifferentValuesWhenDifferentBehaviorsHaveBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenReturn("foo").thenReturn("bar")

    assert "foo" == mock.boom("foo")
    assert "bar" == mock.boom("foo")
  }

  @Test
  void mockShouldThrowExceptionWhenBehaviorIsBeenDefined() {
    def mock = Moggy.mock()

    Moggy.when(mock).boom("foo").thenThrow(new IllegalArgumentException("foo"))

    try {
      mock.boom("foo")
      fail "Excpected illegal argument exception"
    } catch (IllegalArgumentException e) {
      // Expected
    }
  }

  @Test
  void shouldVerifyMockCall() {
    def mock = Moggy.mock()

    mock.boom("foo")

    Moggy.verify(mock).boom("foo")
  }

  @Test
  void shouldVerifyMockCallWithAnyMatcher() {
    def mock = Moggy.mock()

    mock.boom("bar", "foo", null)

    Moggy.verify(mock).boom(anyValue(), "foo", anyValue())
    Moggy.verify(mock).boom("bar", anyValue(), anyValue())
    Moggy.verify(mock).boom(anyValue(), anyValue(), anyValue())
  }

  @Test
  void shouldVerifyWithClosureMatcher() {
    def mock = Moggy.mock()

    mock.boom("test")

    Moggy.verify(mock).boom(Moggy.closureMatcher { it instanceof String })
  }

  @Test
  void verifyShouldFailWithClosureMatcher() {
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


  @Test
  void shouldThrowExceptionWhenVerifyingMockCallAndArgumentsDoNotMath() {
    def mock = Moggy.mock()

    mock.boom("foo")

    try {
      Moggy.verify(mock).boom("bar")
      fail "AssertionError expected"
    } catch (AssertionError e) {
      // excepted
    }
  }

  @Test
  void shouldThrowExceptionWhenVerifyingMockCallAndExpectedMethodHasNotBeenCalled() {
    def mock = Moggy.mock()

    mock.boom("foo")

    try {
      Moggy.verify(mock).foo("foo")
      fail "AssertionError expected"
    } catch (AssertionError e) {
      // excepted
    }
  }

  @Test
  void shouldPassParametersToBehaviorClosures() {
    def mock = Moggy.mock()

    Moggy.when(mock).foo(7).thenDo { param -> assert [7] == param}

    mock.foo(7)
  }

  @Test
  void shouldDefinePropertyDynamically() {
    def mock = Moggy.mock()

    mock.foo = 7

    assert 7 == mock.foo
  }

  @Test
  void shouldReturnNullForUndefinedProperty() {
    def mock = Moggy.mock()

    assert null == mock.foo
  }

  @Test
  void shouldExecuteClosureWhenDereferencingPropertyAndPropertyHasBeenSetBefore() {
    def mock = Moggy.mock()

    mock.foo = { throw new RuntimeException("Caboom") }

    shouldThrow(RuntimeException) { mock.foo }
  }

  @Test
  void shouldFailWhenNumberOfInvocationsDoesNotMatchExpectation () {
    def mock = Moggy.mock()

    mock.foo()

    shouldThrow(AssertionError) {
      Moggy.verify(mock, 2).foo()
    }
  }

  @Test
  void shouldVerifyThatNumberOfInvocationsMatchExpectation () {
    def mock = Moggy.mock()

    mock.foo()
    mock.foo()

    Moggy.verify(mock, 2).foo()
  }

  @Test
  void shouldVerifyNoInvocation () {
    def mock = Moggy.mock()

    Moggy.verify(mock, 0).foo()
  }

  @Test
  void shouldPickCorrectBehaviorWhenDefiningMultipleExpectations () {
    def mock = Moggy.mock()

    Moggy.when(mock).foo(Moggy.anyValue(), 1, 2).thenReturn(2)
    Moggy.when(mock).foo(Moggy.anyValue(), 1, 5).thenReturn(5)

    assert 2 == mock.foo(0, 1, 2)
    assert 5 == mock.foo(0, 1, 5)
  }
}
