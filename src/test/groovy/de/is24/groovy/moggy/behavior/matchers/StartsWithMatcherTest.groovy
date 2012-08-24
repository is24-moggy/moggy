package de.is24.groovy.moggy.behavior.matchers;

import groovy.util.GroovyTestCase

public class StartsWithMatcherTest extends GroovyTestCase {
  
  void testShouldNotMatchNull () {
    assertFalse new StartsWithMatcher(value:"Foo").matches(null)
  }

  void testShouldMatchArgumentThatIsEqualToValue () {
    assertTrue new StartsWithMatcher(value:"Foo").matches("Foo")
  }

  void testNotShouldMatchArgumentThatEndsWithValue () {
    assertFalse new StartsWithMatcher(value:"Foo").matches("BarFoo")
  }

  void testShouldMatchArgumentThatBeginsWithValue () {
    assertTrue new StartsWithMatcher(value:"Foo").matches("FooBar")
  }

  void testShouldNotMatchArgumentThatContainsValue () {
    assertFalse new StartsWithMatcher(value:"Foo").matches("BarFooBar")
  }
}
