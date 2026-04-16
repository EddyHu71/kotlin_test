class MemberViewModel(
    private val repo: MemberRepository
) : ViewModel() {

    fun submit(member: Member) {
        viewModelScope.launch {
            repo.save(member)
        }
    }
}