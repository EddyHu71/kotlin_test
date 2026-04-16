class MemberLocalDataSource(private val dbHelper: AppDatabaseHelper) {

    fun insert(member: Member) {
        val db = dbHelper.writableDatabase
        val v = ContentValues().apply {
            put("id", member.id)
            put("name", member.name)
            put("nik", member.nik)
            put("phone", member.phone)
            put("ktp_main_photo", member.ktpMainPhoto)
            put("ktp_support_photo", member.ktpSupportPhoto)
            put("is_synced", 0)
        }
        db.insert("members", null, v)
    }

    fun getUnsynced(): List<Member> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM members WHERE is_synced = 0", null
        )

        val list = mutableListOf<Member>()

        while (cursor.moveToNext()) {
            list.add(
                Member(
                    id = cursor.getString(0),
                    name = cursor.getString(1),
                    nik = cursor.getString(2),
                    phone = cursor.getString(3),
                    birthPlace = "",
                    birthDate = "",
                    ktpAddress = Address("", "", "", "", ""),
                    domicileAddress = Address("", "", "", "", ""),
                    isSameAddress = false,
                    ktpMainPhoto = cursor.getString(??), // adjust index
                    ktpSupportPhoto = cursor.getString(??),
                    isSynced = false
                )
            )
        }
        return list
    }

    fun markSynced(id: String) {
        val db = dbHelper.writableDatabase
        val v = ContentValues().apply { put("is_synced", 1) }
        db.update("members", v, "id=?", arrayOf(id))
    }
}