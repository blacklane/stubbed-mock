package stubbedmock.domain

import org.junit.Assert
import org.junit.Before
import org.junit.Test
import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubbedMockito.initStubbedMocks
import stubbedmock.filters.types.LazyFilter
import stubbedmock.filters.types.StubbedFilter
import java.lang.reflect.Field

class InvoiceTest {

  @StubbedMock private lateinit var invoice: Invoice
  /*
  Invoice class has bankTransaction val which is lazy type
  since lazy is not supported as a type you can add LazyFilter() to the library and it will skip mocking it
  lazy filter is already implemented in the library so you can directly add to filters

  Invoice class has invoiceHistory val which is BigInteger
  since BigInteger is not supported as a type we need to add custom filter since this filter not implemented
  in the library so we need to create class that implement 'StubbedFilter' interface
  */

  @Before fun setup() {
    initStubbedMocks(this, filters = listOf(LazyFilter(), BigIntegerFilter()))
  }

  @Test fun testValues() {
    Assert.assertEquals(invoice.invoiceName, "invoiceName")
  }
}

class BigIntegerFilter : StubbedFilter {
  override fun isFiltered(field: Field): Boolean = (field.type.simpleName == "BigInteger")
}