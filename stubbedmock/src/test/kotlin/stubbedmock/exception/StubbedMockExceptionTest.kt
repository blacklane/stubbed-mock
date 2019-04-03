package stubbedmock.exception

import stubbedmock.mock.StubbedMocker.stubbedMock
import stubbedmock.models.Calculator
import org.junit.Test

class StubbedMockExceptionTest {

    @Test(expected = StubbedMockException::class)
    fun testNonSupportedType() {
        stubbedMock(Calculator::class.java)
    }
}