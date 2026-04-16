class AuthRepository(
    private val dbHelper: AppDatabaseHelper
) {

    fun saveSession(user: User) {
        val db = dbHelper.writableDatabase
        val v = ContentValues().apply {
            put("email", user.email)
            put("full_name", user.fullName)
            put("token", user.token)
        }
        db.insert("session", null, v)
    }

    fun logout() {
        dbHelper.writableDatabase.execSQL("DELETE FROM session")
    }
}