package stubbedmock.filters

import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubAnnotationParser.initStubbedMocks
import stubbedmock.filters.types.LazyFilter
import stubbedmock.filters.types.TypeFilter
import stubbedmock.mock.Stubber
import stubbedmock.models.User
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class FiltersTest {

  @StubbedMock lateinit var user: User

  @Before fun setup() {
    val stubFilters = listOf(LazyFilter(), TypeFilter("BigInteger"))
    initStubbedMocks(this, filters = stubFilters)
  }

  @Test fun stubbedFilter() {
    assertEquals(user.firstName, "firstName")
    assertEquals(user.phoneNumber, "4901110129")
  }

  @Test fun typeFilter() {
    assertEquals(user.age, null)
  }

  @Test fun filterNormalInvocation() {
    val user = Stubber.run(
      User::class.java, filters = listOf(LazyFilter(), TypeFilter("BigInteger"))
    )

    assertEquals(user.firstName, "firstName")
    assertEquals(user.phoneNumber, "4901110129")
    assertEquals(user.age, null)
  }

}