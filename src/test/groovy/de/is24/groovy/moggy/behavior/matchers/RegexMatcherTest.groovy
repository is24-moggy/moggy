package de.is24.groovy.moggy.behavior.matchers;

import groovy.util.GroovyTestCase

public class RegexMatcherTest extends GroovyTestCase {
  void testShouldNotMatchNull () {
    assertFalse new RegexMatcher(".*Foo.*").matches(null)
  }

  void testShouldMatchArgumentThatIsEqualToValue () {
    assertTrue new RegexMatcher(".*Foo.*").matches("Foo")
  }

  void testShouldMatchArgumentThatEndsWithValue () {
    assertTrue new RegexMatcher(".*Foo.*").matches("BarFoo")
  }

  void testShouldMatchArgumentThatBeginsWithValue () {
    assertTrue new RegexMatcher(".*Foo.*").matches("FooBar")
  }

  void testShouldMatchArgumentThatContainsValue () {
    assertTrue new RegexMatcher(".*Foo.*").matches("BarFooBar")
  }

  void testShouldNotMatchArgumentThatDoesNotContainValue () {
    assertFalse new RegexMatcher(".*Foo.*").matches("BarBar")
  }
}
