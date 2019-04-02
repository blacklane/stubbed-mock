package stubbedmock.factory

import stubbedmock.types.AutoBoxedType
import stubbedmock.types.ClassType
import stubbedmock.types.CollectionType
import stubbedmock.types.DateType
import stubbedmock.types.EnumType
import stubbedmock.types.MapType
import stubbedmock.types.PrimitiveType
import stubbedmock.types.StringType
import stubbedmock.utils.StubbedTypeMatcher.isAutoBoxedPrimitive
import stubbedmock.utils.StubbedTypeMatcher.isCollection
import stubbedmock.utils.StubbedTypeMatcher.isDate
import stubbedmock.utils.StubbedTypeMatcher.isEnum
import stubbedmock.utils.StubbedTypeMatcher.isMap
import stubbedmock.utils.StubbedTypeMatcher.isPrimitive
import stubbedmock.utils.StubbedTypeMatcher.isString

class StubbedTypeFactory {

  fun getValue(clazz: Class<*>, varName: String): StubbedType =
      when {
        isPrimitive(clazz) -> PrimitiveType()
        isAutoBoxedPrimitive(clazz) -> AutoBoxedType()
        isString(clazz) -> StringType(varName)
        isEnum(clazz) -> EnumType()
        isCollection(clazz) -> CollectionType()
        isMap(clazz) -> MapType()
        isDate(clazz) -> DateType()
        else -> ClassType()
      }
}