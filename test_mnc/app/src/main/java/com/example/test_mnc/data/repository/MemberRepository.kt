class MemberRepository(
    private val local: MemberLocalDataSource,
    private val remote: MemberRemoteDataSource
) {

    suspend fun save(member: Member) {
        local.insert(member.copy(isSynced = false))
    }

    suspend fun sync() {
        val data = local.getUnsynced()

        data.forEach {
            val success = remote.upload(it)
            if (success) local.markSynced(it.id)
        }
    }
}