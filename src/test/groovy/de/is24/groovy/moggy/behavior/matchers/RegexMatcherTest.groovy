package de.is24.groovy.moggy.behavior.matchers;


import org.junit.Test

public class RegexMatcherTest {
  @Test
  void shouldNotMatchNull() {
    assert false == new RegexMatcher(".*Foo.*").matches(null)
  }

  @Test
  void shouldMatchArgumentThatIsEqualToValue() {
    assert new RegexMatcher(".*Foo.*").matches("Foo")
  }

  @Test
  void shouldMatchArgumentThatEndsWithValue() {
    assert new RegexMatcher(".*Foo.*").matches("BarFoo")
  }

  @Test
  void shouldMatchArgumentThatBeginsWithValue() {
    assert new RegexMatcher(".*Foo.*").matches("FooBar")
  }

  @Test
  void shouldMatchArgumentThatContainsValue() {
    assert new RegexMatcher(".*Foo.*").matches("BarFooBar")
  }

  @Test
  void shouldNotMatchArgumentThatDoesNotContainValue() {
    assert false == new RegexMatcher(".*Foo.*").matches("BarBar")
  }
}
