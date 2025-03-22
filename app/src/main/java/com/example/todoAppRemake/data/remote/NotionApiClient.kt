package com.example.todoAppRemake.data.remote

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

private const val NOTION_API_URL = "https://api.notion.com/v1/"
private val retrofit =
    Retrofit
        .Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(NOTION_API_URL)
        .build()

object NotionApiClient {
    val client: NotionApiService by lazy {
        retrofit.create(NotionApiService::class.java)
    }
}
