package stubbedmock.types

import stubbedmock.factory.StubbedType

class CollectionType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = emptyList<Any>()
}