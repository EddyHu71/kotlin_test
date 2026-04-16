class MemberFormActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemberFormBinding
    private lateinit var viewModel: MemberViewModel

    private var ktpMainPath: String = ""
    private var ktpSupportPath: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemberFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabaseHelper(this)
        val repo = MemberRepository(
            MemberLocalDataSource(db),
            MemberRemoteDataSource()
        )

        viewModel = MemberViewModel(repo)
        binding.cbSame.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.etDomAddress.setText(binding.etKtpAddress.text.toString())
            }
        }

        // FAKE CAMERA (replace with CameraX later)
        binding.btnKtpMain.setOnClickListener {
            ktpMainPath = "/storage/ktp_main.jpg"
        }

        binding.btnKtpSupport.setOnClickListener {
            ktpSupportPath = "/storage/ktp_support.jpg"
        }

        binding.btnSubmit.setOnClickListener {

            val member = Member(
                id = UUID.randomUUID().toString(),
                name = binding.etName.text.toString(),
                nik = binding.etNik.text.toString(),
                phone = binding.etPhone.text.toString(),
                birthPlace = "",
                birthDate = "",
                ktpAddress = Address(
                    binding.etKtpAddress.text.toString(),
                    "", "", "", ""
                ),
                domicileAddress = Address(
                    binding.etDomAddress.text.toString(),
                    "", "", "", ""
                ),
                isSameAddress = binding.cbSame.isChecked,
                ktpMainPhoto = ktpMainPath,
                ktpSupportPhoto = ktpSupportPath
            )

            viewModel.submit(member)

            Toast.makeText(this, "Saved Offline", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}