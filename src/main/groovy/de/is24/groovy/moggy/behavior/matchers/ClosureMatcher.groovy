package de.is24.groovy.moggy.behavior.matchers

import de.is24.groovy.moggy.behavior.Matcher


class ClosureMatcher implements Matcher {

  Closure closure

  ClosureMatcher(closure) {
    this.closure = closure
  }

  Boolean matches (Object argument) {
    if (argument instanceof Object[]) {      
      Object wrappedArgument = new Object[1]
      wrappedArgument[0] = argument
      argument = wrappedArgument
    }
    return closure.call(argument)
  }
}
