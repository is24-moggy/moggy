package de.is24.groovy.moggy

class MethodParametersTest extends GroovyTestCase {

  void testShouldProduceTheSameHashCodeForEqualInstances () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b", "c"])

    assertTrue one.hashCode() == two.hashCode()
  }

  void testShouldProduceDifferentHashCodeForInequalInstances () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["c", "b", "a"])

    assertFalse one.hashCode() == two.hashCode()
  }

  void testShouldProduceDifferentHashCodeForInequalInstancesOfDifferentSize () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b"])

    assertFalse one.hashCode() == two.hashCode()
  }

  void testShouldInstancesWithEqualValuesShouldBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b", "c"])

    assertEquals one, two
  }

  void testInstancesWithDifferentValuesShouldNotBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["c", "b", "a"])

    assertFalse one == two
  }

  void testInstancesWithDifferentNumberOfValuesShouldNotBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b"])

    assertFalse one == two
  }
}
