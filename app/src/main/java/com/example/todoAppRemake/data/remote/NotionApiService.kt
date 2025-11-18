package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.BuildConfig
import com.example.todoAppRemake.data.remote.model.BlockChildrenResponse
import com.example.todoAppRemake.data.remote.model.NotionDatabasePagesResponse
import com.example.todoAppRemake.data.remote.model.NotionDatabaseResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface NotionApiService {
    @GET("v1/databases/{database_id}")
    suspend fun getDatabase(
        @Path("database_id") databaseId: String,
        @Header("Authorization") auth: String = "Bearer ${BuildConfig.NOTION_API_KEY}",
        @Header("Notion-Version") version: String = BuildConfig.NOTION_VERSION,
    ): NotionDatabaseResponse

    @POST("v1/databases/{database_id}/query")
    suspend fun getDatabasePages(
        @Path("database_id") databaseId: String,
        @Header("Authorization") auth: String = "Bearer ${BuildConfig.NOTION_API_KEY}",
        @Header("Notion-Version") version: String = BuildConfig.NOTION_VERSION,
    ): NotionDatabasePagesResponse

    @GET("v1/blocks/{block_id}/children")
    suspend fun getBlocks(
        @Path("block_id") blockId: String,
        @Header("Authorization") auth: String = "Bearer ${BuildConfig.NOTION_API_KEY}",
        @Header("Notion-Version") version: String = BuildConfig.NOTION_VERSION,
        @Query("page_size") pageSize: Int? = null,
        @Query("start_cursor") startCursor: String? = null,
    ): BlockChildrenResponse
}
