package de.is24.groovy.moggy.behavior

import groovy.mock.interceptor.MockFor
import org.junit.Test;


class InvocationBehaviorTest {
  // We need some matchers here. We could use moggy here, but
  // we should not rely our own tests on our own. Thus, we
  // stick to the old way

  @Test
  void shouldNotMatchDifferentNumberOfArgumentsAndMatchers() {
    def matcherMock = new MockFor(Matcher.class)

    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assert false == behavior.matches(["a", "b"])
  }

  @Test
  void shouldNotMatchWhenMatcherDoesNotMatchArgument() {
    def matcherMock = new MockFor(Matcher.class)
    matcherMock.demand.matches("a") { false }

    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assert false == behavior.matches(["a"])
  }

  @Test
  void shouldMatchWhenMatcherMatchesArgument() {
    def matcherMock = new MockFor(Matcher.class)
    matcherMock.demand.matches("a") { true }

    def behavior = new InvocationBehavior([matcherMock.proxyInstance()])

    assert behavior.matches(["a"])
  }

  @Test
  void shouldMatchWhenMatchersAndArgumentsAreEmpty() {
    def behavior = new InvocationBehavior()

    assert behavior.matches([])
  }

}
