package stubbedmock.types

import stubbedmock.factory.StubbedType

class MapType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = emptyMap<Any, Any>()
}