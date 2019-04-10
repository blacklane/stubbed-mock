package stubbedmock.types

import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubAnnotationParser.initStubbedMocks
import stubbedmock.models.Address
import stubbedmock.models.GeneralTypes
import stubbedmock.models.Status
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TypesTest {

  @StubbedMock lateinit var types: GeneralTypes

  @Before fun setup() = initStubbedMocks(this)

  @Test fun string() {
    assertEquals(types.studentId, "studentId")
  }

  @Test fun boolean() {
    assertEquals(types.booleanNullable, false)
  }

  @Test fun int() {
    assertEquals(types.intNullable, 0)
  }

  @Test fun float() {
    assertEquals(types.floatNullable, 0f)
  }

  @Test fun long() {
    assertEquals(types.longNullable, 0L)
  }

  @Test fun short() {
    assertEquals(types.shortNullable, 0.toShort())
  }

  @Test fun byte() {
    assertEquals(types.byteNullable, 0.toByte())
  }

  @Test fun address() {
    assertEquals((types.studentAddress as Address).streetName, "streetName")
  }

  @Test fun enum() {
    assertEquals(types.studentStatus, Status.SINGLE)
  }

}