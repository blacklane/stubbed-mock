package stubbedmock.types

import stubbedmock.factory.StubbedType
import java.lang.reflect.Array

class PrimitiveType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = getPrimitiveDefaultValue(clazz)

  private fun getPrimitiveDefaultValue(clazz: Class<*>): Any = Array.get(Array.newInstance(clazz, 1), 0)
}