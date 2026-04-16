object ImageUtils {

    suspend fun compress(context: Context, path: String): File {
        val file = File(path)

        return Compressor.compress(context, file) {
            resolution(1280, 720)
            quality(70)
        }
    }
}