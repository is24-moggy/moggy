package de.is24.groovy.moggy;


import org.junit.Test

import static de.is24.groovy.moggy.Moggy.mock
import static de.is24.groovy.moggy.Moggy.when

class MatcherTest {

  @Test
  void any () {
    def mock = mock()

    when(mock).boom(Moggy.anyValue()).thenReturn("foo")

    assert "foo" == mock.boom("foo")
    assert "foo" == mock.boom("bar")
    assert "foo" == mock.boom(4711)
  }

  @Test
  void nullValue () {
    def mock = mock()

    when(mock).boom(Moggy.nullValue()).thenReturn("foo")

    assert "foo" == mock.boom(null)
    assert null == mock.boom(4711)
  }

  @Test
  void notNull () {
    def mock = mock()

    when(mock).boom(Moggy.notNull()).thenReturn("foo")

    assert "foo" == mock.boom(4711)
    assert null == mock.boom(null)
  }

  @Test
  void eq () {
    def mock = mock()

    when(mock).boom(Moggy.eq("foo")).thenReturn("foo")

    assert "foo" == mock.boom("foo")
    assert null == mock.boom("bar")
  }

  @Test
  void contains () {
    def mock = mock()

    when(mock).boom(Moggy.contains("foo")).thenReturn("foo")

    assert "foo" == mock.boom("my foo lady")
    assert null == mock.boom("bar")
  }

  @Test
  void startsWith () {
    def mock = mock()

    when(mock).boom(Moggy.startsWith("foo")).thenReturn("foo")

    assert "foo" == mock.boom("foobar")
    assert null == mock.boom("barfoo")
  }

  @Test
  void endsWith () {
    def mock = mock()

    when(mock).boom(Moggy.endsWith("foo")).thenReturn("foo")

    assert "foo" == mock.boom("barfoo")
    assert null == mock.boom("foobar")
  }

  @Test
  void matches () {
    def mock = mock()

    when(mock).boom(Moggy.matches('^[foo]{3}$')).thenReturn("foo")

    assert "foo" == mock.boom("foo")
    assert null == mock.boom("bar")
  }
}
