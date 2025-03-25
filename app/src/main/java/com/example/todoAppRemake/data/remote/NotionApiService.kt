package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.model.NotionDatabaseResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

private const val NOTION_API_URL = "https://api.notion.com/"
private val retrofit =
    Retrofit
        .Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(NOTION_API_URL)
        .build()

object NotionApiClient {
    val client: NotionApiService by lazy {
        retrofit.create(NotionApiService::class.java)
    }
}

interface NotionApiService {
    @GET("v1/databases/{database_id}")
    suspend fun getDatabase(
        @Path("database_id") databaseId: String,
        @Header("Authorization") auth: String = "Bearer ${BuildConfig.NOTION_API_KEY}",
        @Header("Notion-Version") version: String = BuildConfig.NOTION_VERSION,
    ): NotionDatabaseResponse
}
