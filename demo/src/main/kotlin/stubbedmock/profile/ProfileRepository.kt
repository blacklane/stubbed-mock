package stubbedmock.profile

import io.reactivex.Single

class ProfileRepository {
  fun getUser(): Single<User> = Single.just(retrieveUser())

  // assume this method is getting user from network call or database
  private fun retrieveUser() = User(
    id = 12,
    firstName = "Milos",
    lastName = "Elgendy",
    email = "milos@elgendy.com"
  )
}