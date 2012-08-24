package de.is24.groovy.moggy.behavior.matchers;

import java.util.regex.Pattern;

import de.is24.groovy.moggy.behavior.Matcher

class RegexMatcher implements Matcher {
  private final Pattern pattern
  
  RegexMatcher (String value) {
    pattern = Pattern.compile(value)
  }
  
  Boolean matches(argument) {
    if (!(argument instanceof String)) {
      return false
    }
    
    return pattern.matcher(argument).matches()
	}

}
