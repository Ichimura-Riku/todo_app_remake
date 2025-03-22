package com.example.todoAppRemake.data.remote.model

import com.google.gson.annotations.SerializedName

data class NotionDatabaseResponse(
    @SerializedName("object") val objectType: String,
    val id: String,
    val title: List<Title>,
    val properties: Map<String, Property>,
)

data class Title(
    val type: String,
    val text: Text,
)

data class Text(
    val content: String,
)

data class Property(
    val id: String,
    val name: String,
    val type: String,
)
