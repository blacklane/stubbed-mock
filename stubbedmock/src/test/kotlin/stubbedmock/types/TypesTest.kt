package stubbedmock.types

import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubbedMockito.initStubbedMocks
import stubbedmock.models.Address
import stubbedmock.models.GeneralTypes
import stubbedmock.models.Status
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class TypesTest {

    @StubbedMock
    lateinit var types: GeneralTypes

    @Before
    fun setup() {
        initStubbedMocks(this)
    }

    @Test
    fun testString() {
        Assert.assertEquals(types.studentId, "studentId")
    }

    @Test
    fun testBoolean() {
        Assert.assertEquals(types.booleanNullable, false)
    }

    @Test
    fun testInt() {
        Assert.assertEquals(types.intNullable, 0)
    }

    @Test
    fun testFloat() {
        Assert.assertEquals(types.floatNullable, 0f)
    }

    @Test
    fun testLong() {
        Assert.assertEquals(types.longNullable, 0L)
    }

    @Test
    fun testShort() {
        Assert.assertEquals(types.shortNullable, 0.toShort())
    }

    @Test
    fun testByte() {
        Assert.assertEquals(types.byteNullable, 0.toByte())
    }

    @Test
    fun testAddress() {
        Assert.assertEquals((types.studentAddress as Address).streetName, "streetName")
    }

    @Test
    fun testEnum() {
        Assert.assertEquals(types.studentStatus, Status.SINGLE)
    }
}