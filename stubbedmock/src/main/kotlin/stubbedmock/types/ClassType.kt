package stubbedmock.types

import stubbedmock.factory.StubbedType

class ClassType : StubbedType {
  override fun getMockedValue(clazz: Class<*>): Any = "" // no implementation Needed
}