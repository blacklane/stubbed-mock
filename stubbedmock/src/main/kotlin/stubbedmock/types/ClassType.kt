package stubbedmock.types

import stubbedmock.factory.StubbedType

class ClassType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = "" // no implementation needed
}