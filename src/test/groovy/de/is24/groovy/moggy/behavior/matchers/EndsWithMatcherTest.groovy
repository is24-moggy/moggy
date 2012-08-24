package de.is24.groovy.moggy.behavior.matchers;

import groovy.util.GroovyTestCase

public class EndsWithMatcherTest extends GroovyTestCase {
  
  void testShouldNotMatchNull () {
    assertFalse new EndsWithMatcher(value:"Foo").matches(null)
  }

  void testShouldMatchArgumentThatIsEqualToValue () {
    assertTrue new EndsWithMatcher(value:"Foo").matches("Foo")
  }

  void testShouldMatchArgumentThatEndsWithValue () {
    assertTrue new EndsWithMatcher(value:"Foo").matches("BarFoo")
  }

  void testShouldNotMatchArgumentThatBeginsWithValue () {
    assertFalse new EndsWithMatcher(value:"Foo").matches("FooBar")
  }

  void testShouldNotMatchArgumentThatContainsValue () {
    assertFalse new EndsWithMatcher(value:"Foo").matches("BarFooBar")
  }
}
