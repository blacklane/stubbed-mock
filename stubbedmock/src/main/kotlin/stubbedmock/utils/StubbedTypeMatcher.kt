package stubbedmock.utils

import java.util.Date

private const val BOOLEAN_DEFAULT_VALUE = false
private const val CHARACTER_DEFAULT_VALUE = 'c'
private const val NUMBER_DEFAULT_VALUE = 0

object StubbedTypeMatcher {

  fun isPrimitive(clazz: Class<*>): Boolean = clazz.isPrimitive

  fun isAutoBoxedPrimitive(clazz: Class<*>): Boolean = WRAPPER_TYPES.containsKey(clazz.toString())

  fun isString(clazz: Class<*>): Boolean = clazz.isAssignableFrom(String::class.java)

  fun isEnum(clazz: Class<*>): Boolean = clazz.isEnum

  fun isCollection(clazz: Class<*>): Boolean = Collection::class.java.isAssignableFrom(clazz)

  fun isMap(clazz: Class<*>): Boolean = Map::class.java.isAssignableFrom(clazz)

  fun isDate(clazz: Class<*>): Boolean = clazz.isAssignableFrom(Date::class.java)

  val WRAPPER_TYPES = getWrapperTypes()

  private fun getWrapperTypes() = mapOf(
    "class java.lang.Boolean" to BOOLEAN_DEFAULT_VALUE,
    "class java.lang.Character" to CHARACTER_DEFAULT_VALUE,
    "class java.lang.Byte" to NUMBER_DEFAULT_VALUE.toByte(),
    "class java.lang.Short" to NUMBER_DEFAULT_VALUE.toShort(),
    "class java.lang.Integer" to NUMBER_DEFAULT_VALUE,
    "class java.lang.Long" to NUMBER_DEFAULT_VALUE.toLong(),
    "class java.lang.Float" to NUMBER_DEFAULT_VALUE.toFloat(),
    "class java.lang.Double" to NUMBER_DEFAULT_VALUE.toDouble()
  )

}