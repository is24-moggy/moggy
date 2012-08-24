package de.is24.groovy.moggy

import org.junit.Test

class MethodParametersTest {

  @Test
  void shouldProduceTheSameHashCodeForEqualInstances () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b", "c"])

    assert one.hashCode() == two.hashCode()
  }

  @Test
  void shouldProduceDifferentHashCodeForInequalInstances () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["c", "b", "a"])

    assert one.hashCode() != two.hashCode()
  }

  @Test
  void shouldProduceDifferentHashCodeForInequalInstancesOfDifferentSize () {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b"])

    assert one.hashCode() != two.hashCode()
  }

  @Test
  void shouldInstancesWithEqualValuesShouldBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b", "c"])

    assert one == two
  }

  @Test
  void instancesWithDifferentValuesShouldNotBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["c", "b", "a"])

    assert one != two
  }

  @Test
  void instancesWithDifferentNumberOfValuesShouldNotBeEqual() {
    def one = new MethodParameters(values:["a", "b", "c"])
    def two = new MethodParameters(values:["a", "b"])

    assert one != two
  }
}
