package stubbedmock.types

import stubbedmock.factory.StubbedType
import stubbedmock.utils.StubbedTypeMatcher

class AutoBoxedType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = getAutoBoxingDefaultValue(clazz)

  private fun getAutoBoxingDefaultValue(clazz: Class<*>) = StubbedTypeMatcher.WRAPPER_TYPES.getValue(clazz.toString())
}