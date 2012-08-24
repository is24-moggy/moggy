package de.is24.groovy.moggy.behavior.matchers


class ClosureMatcherTest extends GroovyTestCase{

  void testShouldMatchAnyValue() {
    assertTrue new ClosureMatcher(){true}.matches("any value")
  }

  void testShouldMatchNoValue() {
    assertFalse new ClosureMatcher(){false}.matches("any value")
  }
  
  void testEnsureThatObjectArrayParameterIsWrappedBeforeBeingPassedToMatcher () {
    Object[] parameter = new Object[2]
    
    def closure = { it ->
      assertEquals it, parameter
      return true
    }
    
    assertTrue new ClosureMatcher(closure).matches(parameter as Object[])
  }
}
