package stubbedmock.factory

interface StubbedType {
  fun getMockedValue(clazz: Class<*>): Any
}