package de.is24.groovy.moggy

class MockObjectProxy {

  static <T> T generate (Class<T> type) {
    T proxy = [:].asType(type)
    proxy.metaClass = new MockMetaClass(type, Moggy.mock())

    proxy
  }
}

class MockMetaClass extends MetaClassImpl {
  private final MockObject delegate

  public MockMetaClass (Class clazz, MockObject delegate) {
    super(clazz)
    this.delegate = delegate
  }

  MockObject getDelegate() {
    return delegate
  }

  @Override
  void setProperty(Class sender, Object receiver, String property, Object value, boolean isCallToSuper, boolean fromInsideClass) {
    delegate.setProperty(property, value)
  }

  @Override
  Object getProperty(Class sender, Object object, String name, boolean useSuper, boolean fromInsideClass) {
    switch (name) {
      case "delegate":
        return delegate
      case "class":
        return "$theClass\$MockByMoggy"
      case "metaClass":
        return this
    }

    delegate.getProperty(name)
  }

  @Override
  public Object invokeMethod(final Object object, final String methodName, final Object[] arguments) {
    delegate.invokeMethod(methodName, arguments)
  }
}
