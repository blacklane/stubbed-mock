package stubbedmock.profile

import io.reactivex.Single

class ProfileRepository {
  fun getUser(): Single<User> = Single.just(retrieveUser())

  private fun retrieveUser(): User {
    //assume this method is getting user from network call or database
    return User(id = 12, firstName = "Milos", lastName = "Elgendy", email = "milos@elgendy.com")
  }
}