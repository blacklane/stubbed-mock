package stubbedmock.models

import java.math.BigInteger

class User(
  val firstName: String? = null,
  val email: String? = null,
  val roles: List<String>? = null
) {
  companion object {
    fun printUser() = println("hello user")
  }

  val age: BigInteger? = null

  val phoneNumber by lazy {
    println("I am computing the lazy value")
    "4901110129"
  }
}