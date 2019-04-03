package stubbedmock.domain

import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import stubbedmock.annotation.StubbedField
import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubbedMockito.initStubbedMocks

class AddressTest {

  //to override default values use stubbedField and override the needed values
  @StubbedMock(
    StubbedField("streetName", "Ahmed"),
    StubbedField("streetNumber", intValue = 42),
    StubbedField("streetDiameter", floatValue = 50f)
  )
  lateinit var address: Address

  @Before fun setup() {
    initStubbedMocks(this)
  }

  @Test fun testOverriddenValues() {
    assertEquals(address.streetName, "Ahmed")
    assertEquals(address.streetNumber, 42)
    assertEquals(address.streetDiameter, 50f)
  }

  @Test fun testDefaultValues() {
    assertEquals(address.zone, "zone")
  }

}