class MemberRemoteDataSource {

    suspend fun upload(member: Member, context: Context): Boolean {

        val ktpMainFile = ImageUtils.compress(context, member.ktpMainPhoto)
        val ktpSupportFile = ImageUtils.compress(context, member.ktpSupportPhoto)

        val ktpMainPart = MultipartBody.Part.createFormData(
            "ktp_main",
            ktpMainFile.name,
            ktpMainFile.asRequestBody("image/jpeg".toMediaType())
        )

        val ktpSupportPart = MultipartBody.Part.createFormData(
            "ktp_support",
            ktpSupportFile.name,
            ktpSupportFile.asRequestBody("image/jpeg".toMediaType())
        )

        val json = Gson().toJson(member)
        val dataPart = json.toRequestBody("application/json".toMediaType())

        val response = ApiClient.api.uploadMember(
            dataPart,
            ktpMainPart,
            ktpSupportPart
        )

        return response.isSuccessful
    }
}