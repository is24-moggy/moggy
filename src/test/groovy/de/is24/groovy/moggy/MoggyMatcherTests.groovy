package de.is24.groovy.moggy


class MoggyMatcherTests extends GroovyTestCase {

  void testNotNullShouldNotMatchNullValue() {
    assertFalse Moggy.notNull().matches(null)
  }

  void testNotNullShouldMatchNonNullValue() {
    assertTrue Moggy.notNull().matches("foo")
  }

  void testEqualsShouldNotMatchUnequalValue() {
    assertFalse Moggy.eq("foo").matches("bar")
  }

  void testEqualsShouldMatchEqualValue() {
    assertTrue Moggy.eq("foo").matches("foo")
  }

  void testAnyShouldMatchAnyValue() {
    assertTrue Moggy.anyValue().matches("bar")
    assertTrue Moggy.anyValue().matches(2)
  }

  void testNullShouldNotMatchNotNullValue() {
    assertFalse Moggy.nullValue().matches("foo")
  }

  void testNullShouldMatchNullValue() {
    assertTrue Moggy.nullValue().matches(null)
  }
}
