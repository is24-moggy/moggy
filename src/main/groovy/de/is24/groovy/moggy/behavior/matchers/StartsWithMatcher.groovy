package de.is24.groovy.moggy.behavior.matchers;

import de.is24.groovy.moggy.behavior.Matcher;

class StartsWithMatcher implements Matcher {
  def value
  
  Boolean matches(argument) {
    if (!(argument instanceof String)) {
      return false
    }
    
    return argument.startsWith(value)
	}

}
