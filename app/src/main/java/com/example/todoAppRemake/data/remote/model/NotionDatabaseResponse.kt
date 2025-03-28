package com.example.todoAppRemake.data.remote.model

import com.google.gson.annotations.SerializedName

data class NotionDatabaseResponse(
    @SerializedName("object") val objectType: String,
    val id: String,
    val title: List<NotionDatabaseTitle>,
    val properties: Map<String, NotionDatabaseProperty>,
)

data class NotionDatabaseTitle(
    val type: String,
    val text: NotionDatabaseText,
)

data class NotionDatabaseText(
    val content: String,
)

data class NotionDatabaseProperty(
    val id: String,
    val name: String,
    val type: String,
)
