package stubbedmock.factory

interface StubbedType {
  fun getStubbedValue(clazz: Class<*>): Any
}