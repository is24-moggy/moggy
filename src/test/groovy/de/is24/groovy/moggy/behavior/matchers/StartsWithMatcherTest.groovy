package de.is24.groovy.moggy.behavior.matchers;


import org.junit.Test

public class StartsWithMatcherTest {

  @Test
  void shouldNotMatchNull() {
    assert false == new StartsWithMatcher(value: "Foo").matches(null)
  }

  @Test
  void shouldMatchArgumentThatIsEqualToValue() {
    assert new StartsWithMatcher(value: "Foo").matches("Foo")
  }

  @Test
  void shouldNotMatchArgumentThatEndsWithValue() {
    assert false == new StartsWithMatcher(value: "Foo").matches("BarFoo")
  }

  @Test
  void shouldMatchArgumentThatBeginsWithValue() {
    assert new StartsWithMatcher(value: "Foo").matches("FooBar")
  }

  @Test
  void shouldNotMatchArgumentThatContainsValue() {
    assert false == new StartsWithMatcher(value: "Foo").matches("BarFooBar")
  }
}
