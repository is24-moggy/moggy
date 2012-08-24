package de.is24.groovy.moggy.behavior

class MethodBehavior {
  final String name
  final List invocationBehaviors = []

  MethodBehavior (String name) {
    this.name = name
  }

  InvocationBehavior defineBehavior (List<Object> args, Closure callback) {
    final InvocationBehavior invocationBehavior = new InvocationBehavior(args)
    invocationBehavior.addBehavior(callback)

    invocationBehaviors.add(invocationBehavior)
    invocationBehavior
  }

  Closure findMatchingInvocationBehavior (List<Object> arguments, Integer invocationIndex) {
    def behavior = invocationBehaviors.find { invocationBehavior ->
      return invocationBehavior.matches(arguments)
    }?.getBehavior(invocationIndex)
  }
}