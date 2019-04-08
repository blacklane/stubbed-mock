package stubbedmock.profile

import io.reactivex.observers.DisposableSingleObserver

class ProfilePresenter constructor(
  val view: ProfileView,
  private val repository: ProfileRepository
) {
  fun onShowUserProfile() {
    view.showProgress()

    val disposableUser = repository.getUser()
      .doAfterTerminate { view.hideProgress() }
      .subscribeWith(object : DisposableSingleObserver<User>() {
        override fun onSuccess(user: User) {
          // work with the resulting user
          view.showUserInfo(user)
        }

        override fun onError(e: Throwable) {
          // handle the error case
          view.showError(e)
        }
      })
    disposableUser.dispose()
  }
}