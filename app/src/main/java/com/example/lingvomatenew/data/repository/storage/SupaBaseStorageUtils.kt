//package com.example.lingvomatenew.data.repository.storage
//
//class SupaBaseStorageUtils {
//
//
//    val supabase = createSupabaseClient(
//        "",
//        ""
//    ) {
//        install(Storage)
//    }
//
//    suspend fun uploadImage(uri: Uri): String? {
//        try {
//            val extension = uri.path?.substringAfterLast(".") ?: "jpg"
//            val fileName = "${UUID.randomUUID()}.$extension"
//            val inputStream = context.contentResolver.openInputStream(uri) ?: return null
//            supabase.storage.from(BUCKET_NAME).upload(fileName, inputStream.readBytes())
//            val publicUrl = supabase.storage.from(BUCKET_NAME).publicUrl(fileName)
//            return publicUrl
//        } catch (e: Exception) {
//            e.printStackTrace()
//            return null
//        }
//    }
//
//
//    companion object {
//        const val BUCKET_NAME = "chatter_images"
//    }
//}