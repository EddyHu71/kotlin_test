class SyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {

        val db = AppDatabaseHelper(applicationContext)
        val repo = MemberRepository(
            MemberLocalDataSource(db),
            MemberRemoteDataSource()
        )

        repo.sync()

        return Result.success()
    }
}