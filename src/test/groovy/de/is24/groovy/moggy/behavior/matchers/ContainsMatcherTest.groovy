package de.is24.groovy.moggy.behavior.matchers;


import org.junit.Test

public class ContainsMatcherTest {
  @Test
  void shouldNotMatchNull() {
    assert false == new ContainsMatcher(value: "Foo").matches(null)
  }

  @Test
  void shouldMatchArgumentThatIsEqualToValue() {
    assert new ContainsMatcher(value: "Foo").matches("Foo")
  }

  @Test
  void shouldMatchArgumentThatEndsWithValue() {
    assert new ContainsMatcher(value: "Foo").matches("BarFoo")
  }

  @Test
  void shouldMatchArgumentThatBeginsWithValue() {
    assert new ContainsMatcher(value: "Foo").matches("FooBar")
  }

  @Test
  void shouldMatchArgumentThatContainsValue() {
    assert new ContainsMatcher(value: "Foo").matches("BarFooBar")
  }

  @Test
  void testShouldNotMatchArgumentThatDoesNotContainValue() {
    assert false == new ContainsMatcher(value: "Foo").matches("BarBar")
  }
}
