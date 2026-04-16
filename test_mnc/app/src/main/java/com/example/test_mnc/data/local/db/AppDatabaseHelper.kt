class AppDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, "app.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase) {

        db.execSQL("""
            CREATE TABLE session (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                email TEXT,
                full_name TEXT,
                token TEXT
            )
        """)

        db.execSQL("""
            CREATE TABLE members (
                id TEXT PRIMARY KEY,
                name TEXT,
                nik TEXT,
                phone TEXT,
                birth_place TEXT,
                birth_date TEXT,

                ktp_full_address TEXT,
                ktp_province TEXT,
                ktp_city TEXT,
                ktp_district TEXT,
                ktp_postal_code TEXT,

                domicile_full_address TEXT,
                domicile_province TEXT,
                domicile_city TEXT,
                domicile_district TEXT,
                domicile_postal_code TEXT,

                is_same_address INTEGER,
                ktp_main_photo TEXT,
                ktp_support_photo TEXT,
                is_synced INTEGER DEFAULT 0
            )
        """)
    }

    override fun onUpgrade(db: SQLiteDatabase, old: Int, newV: Int) {}
}