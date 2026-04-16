data class Member(
    val id: String,
    val name: String,
    val nik: String,
    val phone: String,
    val birthPlace: String,
    val birthDate: String,
    val ktpAddress: Address,
    val domicileAddress: Address,
    val isSameAddress: Boolean,
    val ktpMainPhoto: String,
    val ktpSupportPhoto: String,
    val isSynced: Boolean = false
)