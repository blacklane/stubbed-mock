package stubbedmock.models

data class Address(
    val streetName: String? = null,
    val streetNumber: Int? = null,
    val streetDiameter: Float,
    val zone: String
)
