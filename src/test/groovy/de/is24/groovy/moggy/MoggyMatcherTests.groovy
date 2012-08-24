package de.is24.groovy.moggy

import org.junit.Test


class MoggyMatcherTests {

  @Test
  void notNullShouldNotMatchNullValue() {
    assert false == Moggy.notNull().matches(null)
  }

  @Test
  void notNullShouldMatchNonNullValue() {
    assert Moggy.notNull().matches("foo")
  }

  @Test
  void equalsShouldNotMatchUnequalValue() {
    assert false == Moggy.eq("foo").matches("bar")
  }

  @Test
  void equalsShouldMatchEqualValue() {
    assert Moggy.eq("foo").matches("foo")
  }

  @Test
  void anyShouldMatchAnyValue() {
    assert Moggy.anyValue().matches("bar")
    assert Moggy.anyValue().matches(2)
  }

  @Test
  void nullShouldNotMatchNotNullValue() {
    assert false == Moggy.nullValue().matches("foo")
  }

  @Test
  void nullShouldMatchNullValue() {
    assert Moggy.nullValue().matches(null)
  }
}
