package de.is24.groovy.moggy.behavior.matchers;

import groovy.util.GroovyTestCase

public class ContainsMatcherTest extends GroovyTestCase {
  void testShouldNotMatchNull () {
    assertFalse new ContainsMatcher(value:"Foo").matches(null)
  }

  void testShouldMatchArgumentThatIsEqualToValue () {
    assertTrue new ContainsMatcher(value:"Foo").matches("Foo")
  }

  void testShouldMatchArgumentThatEndsWithValue () {
    assertTrue new ContainsMatcher(value:"Foo").matches("BarFoo")
  }

  void testShouldMatchArgumentThatBeginsWithValue () {
    assertTrue new ContainsMatcher(value:"Foo").matches("FooBar")
  }

  void testShouldMatchArgumentThatContainsValue () {
    assertTrue new ContainsMatcher(value:"Foo").matches("BarFooBar")
  }

  void testShouldNotMatchArgumentThatDoesNotContainValue () {
    assertFalse new ContainsMatcher(value:"Foo").matches("BarBar")
  }
}
