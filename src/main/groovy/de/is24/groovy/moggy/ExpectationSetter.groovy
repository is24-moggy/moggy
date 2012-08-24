package de.is24.groovy.moggy

import de.is24.groovy.moggy.behavior.InvocationBehavior

class ExpectationSetter {
  final def mock
  def method
  def args
  private InvocationBehavior invocationBehavior = null
  
  ExpectationSetter (mock) {
    this.mock = mock
  }
  
  Object invokeMethod (String name, Object args) {
    this.method = name
    this.args = args
    return this
  }

  ExpectationSetter thenReturn (value) {
    thenDo { Object[] args -> value }
  }

  ExpectationSetter thenThrow (exception) {
    thenDo { Object[] args -> throw exception }
  }

  ExpectationSetter thenDo (closure) {
    if (!invocationBehavior) {
      invocationBehavior = mock.defineBehavior(method, args, closure)
    } else {
      invocationBehavior.addBehavior(closure)
    }
    return this
  }
}
