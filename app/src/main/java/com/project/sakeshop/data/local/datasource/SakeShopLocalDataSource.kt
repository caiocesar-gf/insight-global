package com.project.sakeshop.data.local.datasource

import com.project.sakeshop.data.local.model.SakeShopDTO
import android.content.Context
import android.util.Log
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SakeShopDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
    private val moshi: Moshi
) {
    fun getSakeShops(): List<SakeShopDTO> {
        return try {
            val fileNames = context.assets.list("")
            if (fileNames?.contains("sakeshop.json") != true) {
                Log.e(TAG, "sakeshop.json not found in assets")
                return emptyList()
            }

            val jsonString = context.assets
                .open("sakeshop.json")
                .bufferedReader()
                .use { it.readText() }

            Log.d(TAG, "JSON content: $jsonString")

            val type = Types.newParameterizedType(
                List::class.java,
                SakeShopDTO::class.java
            )
            val adapter = moshi.adapter<List<SakeShopDTO>>(type)

            val result = adapter.fromJson(jsonString)

            Log.d(TAG, "Parsed result: $result")

            result ?: run {
                Log.e(TAG, "Failed to parse JSON, result was null")
                emptyList()
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error loading sake shops", e)
            emptyList()
        }
    }

    companion object {
        private const val TAG = "SakeShopDataSource"
    }
}