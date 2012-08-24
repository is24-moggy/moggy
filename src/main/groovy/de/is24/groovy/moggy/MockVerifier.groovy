package de.is24.groovy.moggy

class MockVerifier {
  final def mock
  final def expectedNumberOfInvocations

  MockVerifier (mock, expectedNumberOfInvocations) {
    this.mock = mock
    this.expectedNumberOfInvocations = expectedNumberOfInvocations
  }

  Object invokeMethod (String name, Object args) {
    def actualMethodParameters = mock.methodInvocations[name]

    if (!actualMethodParameters && expectedNumberOfInvocations > 0) {
      throw new AssertionError("Expected method call of method ${name} ${expectedNumberOfInvocations} times but method has never been called.")
    }

    def expectedMethodParameters = new MethodParameters(values: args)

    def actualNumberOfInvocations = countInvocationsMatchingParameters(actualMethodParameters, expectedMethodParameters)
    if (expectedNumberOfInvocations != actualNumberOfInvocations) {
      throw new AssertionError("Expected method call of method ${name} with arguments ${args} for ${expectedNumberOfInvocations} times but method has been called ${actualNumberOfInvocations} times.")
    }
  }

  private def countInvocationsMatchingParameters(actualMethodParameters, expectedMethodParameters){
    def result = 0
    actualMethodParameters.each { oneMethodParameters ->
      if (oneMethodParameters.matches(expectedMethodParameters.values)) {
        result += 1
      }
    }
    result
  }
}