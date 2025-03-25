package com.example.todoAppRemake.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
