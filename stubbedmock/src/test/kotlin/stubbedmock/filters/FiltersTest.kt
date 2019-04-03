package stubbedmock.filters

import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubbedMockito.initStubbedMocks
import stubbedmock.filters.types.LazyFilter
import stubbedmock.filters.types.TypeFilter
import stubbedmock.mock.StubbedMocker
import stubbedmock.models.User
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class FiltersTest {

    @StubbedMock
    lateinit var user: User

    @Before
    fun setup() {
        initStubbedMocks(this, filters = listOf(LazyFilter(), TypeFilter("BigInteger")))
    }

    @Test
    fun testStubbedFilter() {
        Assert.assertEquals(user.firstName, "firstName")
        Assert.assertEquals(user.phoneNumber, "4901110129")
    }

    @Test
    fun testTypeFilter() {
        Assert.assertEquals(user.age, null)
    }

    @Test
    fun testFilterNormalInvocation() {
        user = StubbedMocker.stubbedMock(
            User::class.java, filters = listOf(LazyFilter(), TypeFilter("BigInteger"))
        )

        Assert.assertEquals(user.firstName, "firstName")
        Assert.assertEquals(user.phoneNumber, "4901110129")
        Assert.assertEquals(user.age, null)
    }
}