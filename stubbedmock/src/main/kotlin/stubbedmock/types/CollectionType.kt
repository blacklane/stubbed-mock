package stubbedmock.types

import stubbedmock.factory.StubbedType

class CollectionType : StubbedType {

  override fun getMockedValue(clazz: Class<*>): Any = getEmptyCollection()

  private fun getEmptyCollection(): Any = emptyList<Any>()
}