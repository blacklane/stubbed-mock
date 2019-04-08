package stubbedmock.domain

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import stubbedmock.annotation.Stub
import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubAnnotationParser.initStubbedMocks

class AddressTest {

  // to override default values use stubbedField and override the needed values
  @StubbedMock(
    Stub("streetName", "Ahmed"),
    Stub("streetNumber", intValue = 42),
    Stub("streetDiameter", floatValue = 50f)
  )
  lateinit var address: Address

  @Before fun setup() = initStubbedMocks(this)

  @Test fun overriddenValues() {
    with(address) {
      assertEquals(streetName, "Ahmed")
      assertEquals(streetNumber, 42)
      assertEquals(streetDiameter, 50f)
    }
  }

  @Test fun defaultValues() {
    assertEquals(address.zone, "zone")
  }

}