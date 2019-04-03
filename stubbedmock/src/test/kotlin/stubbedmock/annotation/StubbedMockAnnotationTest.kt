package stubbedmock.annotation

import stubbedmock.annotation.StubbedMockito.initStubbedMocks
import stubbedmock.models.Address
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class StubbedMockAnnotationTest {

    @StubbedMock(
        StubbedField("streetName", "Ahmed"),
        StubbedField("streetNumber", intValue = 42),
        StubbedField("streetDiameter", floatValue = 50f)
    )
    lateinit var address: Address

    @Before
    fun setup() {
        initStubbedMocks(this)
    }

    @Test
    fun testOverriddenValues() {
        Assert.assertEquals(address.streetName, "Ahmed")
        Assert.assertEquals(address.streetNumber, 42)
        Assert.assertEquals(address.streetDiameter, 50f)
    }

    @Test
    fun testDefaultValues() {
        Assert.assertEquals(address.zone, "zone")
    }
}