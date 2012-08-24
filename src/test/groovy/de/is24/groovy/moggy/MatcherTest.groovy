package de.is24.groovy.moggy;

import static de.is24.groovy.moggy.Moggy.*
import groovy.util.GroovyTestCase


class MatcherTest extends GroovyTestCase {
  void testAny () {
    def mock = mock()

    when(mock).boom(any()).thenReturn("foo")

    assertEquals "foo", mock.boom("foo")
    assertEquals "foo", mock.boom("bar")
    assertEquals "foo", mock.boom(4711)
  }

  void testNullValue () {
    def mock = mock()

    when(mock).boom(nullValue()).thenReturn("foo")

    assertEquals "foo", mock.boom(null)
    assertEquals null, mock.boom(4711)
  }

  void testNotNull () {
    def mock = mock()

    when(mock).boom(notNull()).thenReturn("foo")

    assertEquals "foo", mock.boom(4711)
    assertEquals null, mock.boom(null)
  }

  void testEq () {
    def mock = mock()

    when(mock).boom(eq("foo")).thenReturn("foo")

    assertEquals "foo", mock.boom("foo")
    assertEquals null, mock.boom("bar")
  }

  void testContains () {
    def mock = mock()

    when(mock).boom(Moggy.contains("foo")).thenReturn("foo")

    assertEquals "foo", mock.boom("my foo lady")
    assertEquals null, mock.boom("bar")
  }

  void testStartsWith () {
    def mock = mock()

    when(mock).boom(startsWith("foo")).thenReturn("foo")

    assertEquals "foo", mock.boom("foobar")
    assertEquals null, mock.boom("barfoo")
  }

  void testEndsWith () {
    def mock = mock()

    when(mock).boom(endsWith("foo")).thenReturn("foo")

    assertEquals "foo", mock.boom("barfoo")
    assertEquals null, mock.boom("foobar")
  }

  void testMatches () {
    def mock = mock()

    when(mock).boom(matches('^[foo]{3}$')).thenReturn("foo")

    assertEquals "foo", mock.boom("foo")
    assertEquals null, mock.boom("bar")
  }
}
