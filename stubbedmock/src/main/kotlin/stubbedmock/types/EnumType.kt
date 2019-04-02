package stubbedmock.types

import stubbedmock.factory.StubbedType

class EnumType : StubbedType {

  override fun getMockedValue(clazz: Class<*>): Any = getEnumFirstValue(clazz)

  private fun getEnumFirstValue(clazz: Class<*>): Any = clazz.declaredFields[0].get(Any())
}