package de.is24.groovy.moggy

/**
 * Utility class providing convenience methods for common assertions.
 * @author Alexander Metzner
 */
final class AssertionUtils {
  private AssertionUtils() {
    // Use static methods
  }

  static void shouldThrow (Class<? extends Throwable> expectedException, Closure closure) {
    try {
      closure()
    } catch (Throwable anyException) {
      if (anyException.class == expectedException) {
        return
      }
    }

    throw new AssertionError("Expected ${expectedException.class.name}")
  }
}
