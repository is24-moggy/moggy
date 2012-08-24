package de.is24.groovy.moggy.behavior

import de.is24.groovy.moggy.behavior.matchers.ClosureMatcher

class InvocationBehavior {
  final List<Matcher> argumentMatchers = []
  final List<Closure> invocationBehaviors = []

  InvocationBehavior(List<Object> methodArguments) {
    methodArguments.each { argument ->
      if (argument instanceof Matcher) {
        argumentMatchers.add(argument)
      } else {
        argumentMatchers.add(new ClosureMatcher({it == argument}))
      }
    }
  }

  boolean matches(List<Object> arguments) {
    if (arguments.size() != argumentMatchers.size()) {
      return false
    }
    
    if (argumentMatchers.size() == 0) {
      return true
    }
    
    for (index in 0..(arguments.size() - 1)) {
      if (!argumentMatchers[index].matches(arguments[index])) {
        return false
      }
    }
    
    return true
  }

  void addBehavior (closure) {
    invocationBehaviors.add(closure)
  }

  Closure getBehavior (Integer invocationIndex) {
    invocationBehaviors[Math.min(invocationIndex, invocationBehaviors.size() -1)]
  }
}
