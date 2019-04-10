package stubbedmock.types

import stubbedmock.factory.StubbedType

class StringType(var name: Any) : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = name
}