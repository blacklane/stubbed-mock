package stubbedmock.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubAnnotationParser.initStubbedMocks
import stubbedmock.filters.types.LazyFilter
import stubbedmock.filters.types.StubFilter
import java.lang.reflect.Field

class InvoiceTest {

  @StubbedMock lateinit var invoice: Invoice

  // Invoice class has one lazy (bankTransaction) and one BigInteger property (invoiceHistory)
  // these are not stubbed by default, they require additional stubbing filters
  val stubFilters = listOf(LazyFilter(), BigIntegerFilter())

  @Before fun setup() = initStubbedMocks(this, stubFilters)

  @Test fun values() {
    assertEquals(invoice.invoiceName, "invoiceName")
  }

}

class BigIntegerFilter : StubFilter {
  override fun isFiltered(field: Field) = field.type.simpleName == "BigInteger"
}