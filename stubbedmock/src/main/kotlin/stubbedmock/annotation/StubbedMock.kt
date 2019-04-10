package stubbedmock.annotation

import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationRetention.RUNTIME

@Target(FIELD)
@Retention(RUNTIME)
annotation class StubbedMock(vararg val stub: Stub)

@Target(FIELD)
@Retention(RUNTIME)
annotation class Stub(
  val key: String,
  val stringValue: String = "",
  val charValue: Char = 'c',
  val booleanValue: Boolean = false,
  val intValue: Int = 0,
  val floatValue: Float = 0.0f,
  val longValue: Long = 0L,
  val doubleValue: Double = 0.0,
  val shortValue: Short = 0.toShort(),
  val byteValue: Byte = 0.toByte()
)