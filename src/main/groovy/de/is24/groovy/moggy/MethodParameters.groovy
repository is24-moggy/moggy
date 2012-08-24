package de.is24.groovy.moggy

import de.is24.groovy.moggy.behavior.Matcher

class MethodParameters {
  def values

  Boolean matches(otherArguments) {
    if (values.size() != otherArguments.size()) {
      return false
    }

    Boolean matches = true

    for (def i = 0; i < values.size(); ++i) {
      def otherArgument = otherArguments[i]
      def myArgument = values[i]

      if (isMatcher(otherArgument)) {
          matches = otherArgument.matches(myArgument)
      }
      else {
        matches = myArgument == otherArgument
      }
      if(!matches){
        return matches
      }
    }
    return matches
  }

  private boolean isMatcher(argument) {
    return argument instanceof Matcher
  }

  boolean equals(parameters) {
    return matches(parameters.values)
  }

  int hashCode() {
    int result = 0

    values.each { value ->
      result = (result + value.hashCode()) * 13
    }

    return result
  }
}
