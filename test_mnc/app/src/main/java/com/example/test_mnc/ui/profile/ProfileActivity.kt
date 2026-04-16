class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var authRepo: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        if (NetworkUtils.isOnline(this)) {
            WorkManager.getInstance(this).enqueue(
                OneTimeWorkRequestBuilder<SyncWorker>().build()
            )
        }
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabaseHelper(this)
        authRepo = AuthRepository(db)

        val cursor = db.readableDatabase.rawQuery(
            "SELECT * FROM session LIMIT 1", null
        )

        if (cursor.moveToFirst()) {
            binding.tvEmail.text = cursor.getString(1)
            binding.tvName.text = cursor.getString(2)
        }

        binding.btnForm.setOnClickListener {
            startActivity(Intent(this, MemberFormActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            authRepo.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }
}