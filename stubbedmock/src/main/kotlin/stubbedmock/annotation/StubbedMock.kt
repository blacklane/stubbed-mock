package stubbedmock.annotation

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class StubbedMock(vararg val value: StubbedField)

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class StubbedField(
    val key: String,
    val stringValue: String = "",
    val charValue: Char = 'c',
    val booleanValue: Boolean = false,
    val intValue: Int = 0,
    val floatValue: Float = 0.toFloat(),
    val longValue: Long = 0.toLong(),
    val doubleValue: Double = 0.toDouble(),
    val shortValue: Short = 0.toShort(),
    val byteValue: Byte = 0.toByte()
)