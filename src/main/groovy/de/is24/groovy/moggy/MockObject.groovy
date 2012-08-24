package de.is24.groovy.moggy

import de.is24.groovy.moggy.behavior.InvocationBehavior;
import de.is24.groovy.moggy.behavior.MethodBehavior;

class MockObject {
  def methodBehaviors = [:]
  def methodInvocations = [:]
  def properties = [:]
  
  def getProperty (String propertyName) {
    if (propertyName == "methodBehaviors") {
      return methodBehaviors
    }

    if (propertyName == "methodInvocations") {
      return methodInvocations
    }
    
    if (properties.containsKey(propertyName)) {
      def value = properties[propertyName]
      
      if (value instanceof Closure) {
        return value()
      }
      return value 
    }
    
    return null
  }

  void setProperty(String propertyName, Object propertyValue){
    properties[propertyName] = propertyValue;
  }

  Object invokeMethod (String name, Object args) {
    def invocationIndex = addInvocationAndReturnInvocationIndex(name, args)

    returnBehavior(name, args, invocationIndex)
  }
  
  InvocationBehavior defineBehavior (String name, Object args, Closure callback) {
    final List<Object> arguments = asArgumentList(args)
    MethodBehavior behavior = methodBehaviors[name]
    
    if (!behavior) {
      behavior = new MethodBehavior(name)
      methodBehaviors.put(name, behavior)
    }
    
    return behavior.defineBehavior(arguments, callback)
  }

  private def returnBehavior (name, args, invocationIndex) {
    final List<Object> arguments = asArgumentList(args)
    
    def invocationBehavior = methodBehaviors[name]?.findMatchingInvocationBehavior(arguments, invocationIndex)
    
    if (!invocationBehavior) {
      return null
    }
    
    return invocationBehavior(args)
  }

  private Integer addInvocationAndReturnInvocationIndex (String name, Object args) {
    def invocations = methodInvocations[name]

    if (!invocations) {
      invocations = []
      methodInvocations[name] = invocations
    }

    invocations.add(new MethodParameters(values: args))
    invocations.size() - 1
  }
  
  private List<Object> asArgumentList (Object arguments) {
    def result = []
    arguments.each { argument -> result.add(argument) }
    result
  }
}
