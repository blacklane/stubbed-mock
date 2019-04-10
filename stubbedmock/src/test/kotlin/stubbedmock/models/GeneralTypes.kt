package stubbedmock.models

import java.util.Date

class GeneralTypes {
  var studentId: String? = null
  var studentName: String? = null

  var booleanNullable: Boolean? = null
  var booleanVar: Boolean = false

  var byteNullable: Byte? = null
  var byteVar: Byte = 2.toByte()

  var charNullable: Char? = null
  var charVar: Char = '2'

  var shortNullable: Short? = null
  var shortVar: Short = 0.toShort()

  var intNullable: Int? = null
  var intVar: Int = 0

  var longNullable: Long? = null
  var longVar: Long = 0

  var floatNullable: Float? = null
  var floatVar: Float = 0F

  var doubleNullable: Double? = null
  var doubleVar: Double = 0.0

  var studentStatus: Status? = null
  var studentAddress: Address? = null
  var date: Date? = null
}