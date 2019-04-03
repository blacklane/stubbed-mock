package stubbedmock.profile

data class User(
  val id: Int,
  val firstName: String,
  val lastName: String,
  val email: String,
  val company: String? = null
)