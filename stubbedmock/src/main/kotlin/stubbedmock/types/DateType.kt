package stubbedmock.types

import stubbedmock.factory.StubbedType
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateType : StubbedType {
  override fun getStubbedValue(clazz: Class<*>): Any = getStaticDate()

  private fun getCurrentDate(): Any = Date()

  private fun getStaticDate(): Any = parseDate("2018-07-03")

  private fun parseDate(date: String): Date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
}