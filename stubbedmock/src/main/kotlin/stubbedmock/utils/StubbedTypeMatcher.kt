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

  private fun getWrapperTypes(): Map<String, Any> {
    val ret = HashMap<String, Any>()
    ret.put("class java.lang.Boolean", BOOLEAN_DEFAULT_VALUE)
    ret.put("class java.lang.Character", CHARACTER_DEFAULT_VALUE)
    ret.put("class java.lang.Byte", NUMBER_DEFAULT_VALUE.toByte())
    ret.put("class java.lang.Short", NUMBER_DEFAULT_VALUE.toShort())
    ret.put("class java.lang.Integer", NUMBER_DEFAULT_VALUE)
    ret.put("class java.lang.Long", NUMBER_DEFAULT_VALUE.toLong())
    ret.put("class java.lang.Float", NUMBER_DEFAULT_VALUE.toFloat())
    ret.put("class java.lang.Double", NUMBER_DEFAULT_VALUE.toDouble())
    return ret
  }
}