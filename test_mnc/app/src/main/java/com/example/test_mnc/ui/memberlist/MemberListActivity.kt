class MemberListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMemberListBinding
    private lateinit var viewModel: MemberListViewModel
    private lateinit var adapter: MemberAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMemberListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = AppDatabaseHelper(this)
        val repo = MemberRepository(
            MemberLocalDataSource(db),
            MemberRemoteDataSource()
        )

        viewModel = MemberListViewModel(repo)

        adapter = MemberAdapter(emptyList())
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = adapter

        observe()

        binding.btnSyncAll.setOnClickListener {
            viewModel.syncAll(this)
        }

        viewModel.loadData()
    }

    private fun observe() {
        viewModel.members.observe(this) {
            adapter.update(it)
        }

        viewModel.loading.observe(this) {
            binding.progress.visibility = if (it) View.VISIBLE else View.GONE
        }
    }
}