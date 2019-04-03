package stubbedmock.domain

import java.math.BigInteger

data class Invoice(
  val invoiceId: Int? = null,
  val invoiceNumber: Long? = null,
  val invoiceName: String
) {
  val invoiceHistory: BigInteger? = null

  val bankTransaction by lazy {
    println("I am computing the lazy value")
    "4901110129"
  }

}
