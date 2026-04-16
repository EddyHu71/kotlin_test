interface ApiService {

    @Multipart
    @POST("member")
    suspend fun uploadMember(
        @Part("data") data: RequestBody,
        @Part ktpMain: MultipartBody.Part,
        @Part ktpSupport: MultipartBody.Part
    ): Response<Unit>
}