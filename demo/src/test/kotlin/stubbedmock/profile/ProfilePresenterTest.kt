package stubbedmock.profile

import com.nhaarman.mockito_kotlin.given
import com.nhaarman.mockito_kotlin.inOrder
import com.nhaarman.mockito_kotlin.verifyNoMoreInteractions
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks
import stubbedmock.annotation.StubbedMock
import stubbedmock.annotation.StubAnnotationParser.initStubbedMocks

class ProfilePresenterTest {

  @Mock lateinit var repository: ProfileRepository
  @Mock lateinit var view: ProfileView

  @StubbedMock lateinit var user: User

  private lateinit var presenter: ProfilePresenter

  @Before fun setup() {
    initMocks(this)
    initStubbedMocks(this)

    given(repository.getUser()).willReturn(Single.just(user))

    presenter = ProfilePresenter(view, repository)
  }

  @Test fun onShowUserProfile() {
    presenter.onShowUserProfile()

    inOrder(view, repository) {
      verify(view).showProgress()
      verify(repository).getUser()
      verify(view).showUserInfo(user)
      verify(view).hideProgress()
    }
    verifyNoMoreInteractions(view, repository)
  }

}