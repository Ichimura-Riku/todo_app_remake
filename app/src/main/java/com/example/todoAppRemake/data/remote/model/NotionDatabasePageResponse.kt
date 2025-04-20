package com.example.todoAppRemake.data.remote.model

import com.google.gson.annotations.SerializedName

// Notionのデータベース内のページのレスポンス

data class NotionDatabasePagesResponse(
    @SerializedName("object") val objectType: String,
    val results: List<NotionDatabasePage>,
)

data class NotionDatabasePage(
    @SerializedName("object") val objectType: String,
    val id: String,
    val properties: NotionDatabasePageProperty,
)

data class NotionDatabasePageProperty(
    @SerializedName("Checkbox")
    val checkbox: CheckboxProperty,
    @SerializedName("Date")
    val date: DateProperty,
    @SerializedName("Name")
    val name: NameProperty,
)

data class CheckboxProperty(
    val id: String,
    @SerializedName("checkbox")
    val isChecked: Boolean,
)

data class DateProperty(
    val id: String,
    val date: DatePropertyValue,
)

data class DatePropertyValue(
    val start: String,
    val end: String?,
    val timeZone: String,
)

data class NameProperty(
    val id: String,
    val title: List<TitlePropertyValue>,
)

data class TitlePropertyValue(
    val type: String,
    @SerializedName("plain_text")
    val plainText: String,
)
