package stubbedmock.annotation

import stubbedmock.filters.types.StubFilter
import stubbedmock.mock.Stubber
import java.lang.reflect.Field

object StubAnnotationParser {

  fun initStubbedMocks(
    instance: Any,
    filters: List<StubFilter> = emptyList(),
    classFields: Map<String, Any> = emptyMap()
  ) {
    instance.javaClass.declaredFields
      .filter {
        it.getAnnotation(StubbedMock::class.java) != null
      }.forEach {
        processStubbing(instance, it, classFields, filters)
      }
  }

  private fun processStubbing(
    objectInstance: Any,
    field: Field,
    classFields: Map<String, Any>,
    filters: List<StubFilter>
  ) {
    field.isAccessible = true
    val stubbedFieldsMap = processAnnotationVariables(field)
    val value = Stubber.run(field.type, classFields, stubbedFieldsMap, filters)
    field.set(objectInstance, value)
  }

  private fun processAnnotationVariables(field: Field): MutableMap<String, Any> {
    val stubbedFields = field.getAnnotation(StubbedMock::class.java).stub
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

  private fun getStubbedFieldValue(fieldType: Class<*>, stub: Stub): Any = when {
    fieldType == Char::class.java || fieldType.toString() == "class java.lang.Character" -> stub.charValue
    fieldType == Boolean::class.java || fieldType.toString() == "class java.lang.Boolean" -> stub.booleanValue
    fieldType == Int::class.java || fieldType.toString() == "class java.lang.Integer" -> stub.intValue
    fieldType == Float::class.java || fieldType.toString() == "class java.lang.Float" -> stub.floatValue
    fieldType == Long::class.java || fieldType.toString() == "class java.lang.Long" -> stub.longValue
    fieldType == Double::class.java || fieldType.toString() == "class java.lang.Double" -> stub.doubleValue
    fieldType == Short::class.java || fieldType.toString() == "class java.lang.Short" -> stub.shortValue
    fieldType == Byte::class.java || fieldType.toString() == "class java.lang.Byte" -> stub.byteValue
    else -> stub.stringValue
  }
}