package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.model.NotionDatabaseResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface NotionApiService {
    @GET("v1/databases/{database_id}")
    suspend fun getDatabase(
        @Path("database_id") databaseId: String,
        @Header("Authorization") auth: String = "Bearer ${BuildConfig.NOTION_API_KEY}",
        @Header("Notion-Version") version: String = BuildConfig.NOTION_VERSION,
    ): NotionDatabaseResponse
}
