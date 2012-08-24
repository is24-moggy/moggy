package de.is24.groovy.moggy.behavior.matchers

import org.junit.Test


class ClosureMatcherTest {

  @Test
  void shouldMatchAnyValue() {
    assert new ClosureMatcher({true}).matches("any value")
  }

  @Test
  void shouldMatchNoValue() {
    assert false == new ClosureMatcher({false}).matches("any value")
  }

  @Test
  void ensureThatObjectArrayParameterIsWrappedBeforeBeingPassedToMatcher () {
    Object[] parameter = new Object[2]
    parameter[0] = 'a'
    parameter[1] = 'b'

    def closure = { it ->
      assert it == [parameter]
      return true
    }
    
    assert new ClosureMatcher(closure).matches(parameter as Object[])
  }
}
