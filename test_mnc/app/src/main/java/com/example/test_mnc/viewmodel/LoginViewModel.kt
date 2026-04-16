class LoginViewModel(
    private val authRepo: AuthRepository
) : ViewModel() {

    fun login(email: String, password: String) {
        // fake API
        val user = User(email, "Eddy", "token123")
        authRepo.saveSession(user)
    }
}