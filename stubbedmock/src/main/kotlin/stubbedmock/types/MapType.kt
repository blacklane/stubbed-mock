package stubbedmock.types

import stubbedmock.factory.StubbedType

class MapType : StubbedType {

  override fun getMockedValue(clazz: Class<*>): Any = getEmptyMap()

  private fun getEmptyMap(): Any = emptyMap<Any, Any>()
}