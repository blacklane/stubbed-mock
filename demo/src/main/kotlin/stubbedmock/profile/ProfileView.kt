package stubbedmock.profile

interface ProfileView {
  fun showProgress()
  fun showUserInfo(user: User)
  fun showError(e: Throwable)
  fun hideProgress()
}