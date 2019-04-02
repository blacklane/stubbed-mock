package stubbedmock.annotation

import stubbedmock.filters.types.StubbedFilter
import stubbedmock.mock.StubbedMocker
import java.lang.reflect.Field

object StubbedMockito {

  fun initStubbedMocks(
      instance: Any,
      classFields: Map<String, Any> = emptyMap(),
      filters: List<StubbedFilter> = emptyList()
  ) {
    for (field in instance.javaClass.declaredFields) {
      if (field.getAnnotation(StubbedMock::class.java) != null) {
        processMocking(instance, field, classFields, filters)
      }
    }
  }

  private fun processMocking(
      objectInstance: Any,
      field: Field,
      classFields: Map<String, Any>,
      filters: List<StubbedFilter>
  ) {
    field.isAccessible = true
    val stubbedFieldsMap = processAnnotationVariables(field)
    field.set(objectInstance, StubbedMocker.stubbedMock(field.type, classFields, stubbedFieldsMap, filters))
  }

  private fun processAnnotationVariables(field: Field): MutableMap<String, Any> {
    val stubbedFields = field.getAnnotation(StubbedMock::class.java).value
    val stubbedFieldsMap = mutableMapOf<String, Any>()

    stubbedFields.forEach {
      for (classField in field.type.declaredFields) {
        if (classField.name == it.key) {
          stubbedFieldsMap[it.key] = getStubbedFieldValue(classField.type, it)
          break
        }
      }
    }
    return stubbedFieldsMap
  }

  private fun getStubbedFieldValue(fieldType: Class<*>, stubbedField: StubbedField): Any {
    return when {
      fieldType == Char::class.java || fieldType.toString() == "class java.lang.Character" -> stubbedField.charValue
      fieldType == Boolean::class.java || fieldType.toString() == "class java.lang.Boolean" -> stubbedField.booleanValue
      fieldType == Int::class.java || fieldType.toString() == "class java.lang.Integer" -> stubbedField.intValue
      fieldType == Float::class.java || fieldType.toString() == "class java.lang.Float" -> stubbedField.floatValue
      fieldType == Long::class.java || fieldType.toString() == "class java.lang.Long" -> stubbedField.longValue
      fieldType == Double::class.java || fieldType.toString() == "class java.lang.Double" -> stubbedField.doubleValue
      fieldType == Short::class.java || fieldType.toString() == "class java.lang.Short" -> stubbedField.shortValue
      fieldType == Byte::class.java || fieldType.toString() == "class java.lang.Byte" -> stubbedField.byteValue
      else -> stubbedField.stringValue
    }
  }
}