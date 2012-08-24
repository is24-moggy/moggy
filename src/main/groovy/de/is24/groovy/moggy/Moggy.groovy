package de.is24.groovy.moggy

import de.is24.groovy.moggy.behavior.Matcher
import de.is24.groovy.moggy.behavior.matchers.ContainsMatcher
import de.is24.groovy.moggy.behavior.matchers.EndsWithMatcher
import de.is24.groovy.moggy.behavior.matchers.RegexMatcher
import de.is24.groovy.moggy.behavior.matchers.StartsWithMatcher
import de.is24.groovy.moggy.behavior.matchers.ClosureMatcher

/**
 * Central facade to the functionality provided by moggy.
 * <p>
 * This class provides convenient methods to create mock objects,
 * define expectations and verify the results.
 * This class' methods are intended for static imports.
 * </p>
 * <p>
 * Here is a sample session demonstrating the use of the methods
 * defined in this class:
 * <pre>
 * def mock = mock()
 * when(mock).foo(any()).thenReturn("foo").thenReturn("bar")
 *
 * assertEquals "foo", mock.foo(1)
 * assertEquals "bar", mock.foo(1)
 *
 * verify(mock).foo(1)
 * </pre>
 * </p>
 *
 * @author Alexander Metzner
 */
class Moggy {
  private static final Matcher ANY_MATCHER = new ClosureMatcher({true})
  private static final Matcher NULL_MATCHER = new ClosureMatcher({it == null})
  private static final Matcher NOT_NULL_MATCHER = new ClosureMatcher({it != null})

  /**
   * @return a fresh new mock object ready for use
   */
  static def mock () {
    new MockObject()
  }

  /**
   * Used to define behavior on the given mock object. 
   * See this class' comment for an example.
   *
   * @param mock the mock to define behavior for
   * @return an expectation setter
   */
  static def when (mock) {
    new ExpectationSetter(mock)
  }

  /**
   * Used to verify invocations made on this mock.
   * See this class' comment for an example.
   *
   * @param mock the mock to verify invocations for
   * @param expectedNumberOfInvocations expected number of invocations (defaults to 1)
   * @return a mock verifier
   */
  static def verify (mock, expectedNumberOfInvocations=1) {
    new MockVerifier(mock, expectedNumberOfInvocations)
  }

  /**
   * @deprecated use anyValue instead
   * @return an argument matcher matching all values.
   */
  static def any () {
    return ANY_MATCHER
  }

    /**
   * @return an argument matcher matching all values.
   */
  static def anyValue () {
    return ANY_MATCHER
  }

  /**
   * @return an argument matcher matching <code>null</code> values.
   */
  static def nullValue () {
    return NULL_MATCHER
  }

  /**
   * @return an argument matcher matching all non-null values.
   */
  static def notNull () {
    return NOT_NULL_MATCHER
  }

  /**
   * @return an argument matcher matching closure value.
   */
  static def closureMatcher(closure) {
    return new ClosureMatcher(closure)
  }

  /**
   * @return an argument matcher matching the given value.
   * Notice that this is the default when using bare values
   * in expectation setters. This method is provided for those cases
   * where eq(...) can improve the readability.
   */
  static def eq (value) {
    return new ClosureMatcher({it == value})
  }

  /**
   * @return convenience method to return a {@link ContainsMatcher}
   */
  static def contains (value) {
    return new ContainsMatcher(value:value)
  }

  /**
   * @return convenience method to return a {@link StartsWithMatcher}
   */
  static def startsWith (value) {
    return new StartsWithMatcher(value:value)
  }

  /**
   * @return convenience method to return a {@link EndsWithMatcher}
   */
  static def endsWith (value) {
    return new EndsWithMatcher(value:value)
  }

    /**
   * @return convenience method to return a {@link RegexMatcher}
   */
  static def matches (value) {
    return new RegexMatcher(value)
  }
}
