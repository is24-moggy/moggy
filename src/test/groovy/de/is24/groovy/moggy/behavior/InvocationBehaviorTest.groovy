package de.is24.groovy.moggy.behavior

import groovy.mock.interceptor.MockFor;


class InvocationBehaviorTest extends GroovyTestCase {
  // We need some matchers here. We could use moggy here, but
  // we should not rely our own tests on our own. Thus, we
  // stick to the old way

  void testShouldNotMatchDifferentNumberOfArgumentsAndMatchers () {
    def matcherMock = new MockFor(Matcher.class)

    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assertFalse behavior.matches(["a", "b"])
  }

  void testShouldNotMatchWhenMatcherDoesNotMatchArgument () {
    def matcherMock = new MockFor(Matcher.class)
    matcherMock.demand.matches("a") { false }

    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assertFalse behavior.matches(["a"])
  }

  void testShouldMatchWhenMatcherMatchesArgument () {
    def matcherMock = new MockFor(Matcher.class)
    matcherMock.demand.matches("a") { true }
    
    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assertTrue behavior.matches(["a"])
  }

    void testShouldMatchWhenMatchersAndArgumentsAreEmpty () {
    def behavior = new InvocationBehavior()
    
    assertTrue behavior.matches([])
  }

}
