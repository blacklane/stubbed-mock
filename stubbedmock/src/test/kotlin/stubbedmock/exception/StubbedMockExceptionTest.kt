package stubbedmock.exception

import stubbedmock.mock.Stubber.run
import stubbedmock.models.Calculator
import org.junit.Test

class StubbedMockExceptionTest {

  @Test(expected = StubbedMockException::class)
  fun nonSupportedType() {
    run(Calculator::class.java)
  }

}