package stubbedmock.types

import stubbedmock.factory.StubbedType

class StringType(var name: Any) : StubbedType {

  override fun getMockedValue(clazz: Class<*>): Any = getFieldDefaultName()

  private fun getFieldDefaultName(): Any = name
}