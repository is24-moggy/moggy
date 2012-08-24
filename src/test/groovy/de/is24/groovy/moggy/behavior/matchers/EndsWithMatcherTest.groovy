package de.is24.groovy.moggy.behavior.matchers;


import org.junit.Test

public class EndsWithMatcherTest {

  @Test
  void shouldNotMatchNull() {
    assert false == new EndsWithMatcher(value: "Foo").matches(null)
  }

  @Test
  void shouldMatchArgumentThatIsEqualToValue() {
    assert new EndsWithMatcher(value: "Foo").matches("Foo")
  }

  @Test
  void shouldMatchArgumentThatEndsWithValue() {
    assert new EndsWithMatcher(value: "Foo").matches("BarFoo")
  }

  @Test
  void shouldNotMatchArgumentThatBeginsWithValue() {
    assert false == new EndsWithMatcher(value: "Foo").matches("FooBar")
  }

  @Test
  void shouldNotMatchArgumentThatContainsValue() {
    assert false == new EndsWithMatcher(value: "Foo").matches("BarFooBar")
  }
}
