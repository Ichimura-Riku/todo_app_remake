package com.example.todoAppRemake.domain.model

data class NotionDatabase(
    val id: String, // NotionのデータベースID
    val title: String, // データベース名
    val properties: List<NotionProperty>, // データベース内のカラム情報
)

data class NotionPage(
    val id: String, // ページID
    val parentDatabaseId: String, // 紐づいているデータベースID
    val properties: Map<String, NotionPropertyValue>, // ページのフィールド情報
)

data class NotionProperty(
    val name: String, // プロパティ名
    val type: String, // プロパティの型 (text, number, select, etc.)
)

data class NotionPropertyValue(
    val type: String, // フィールドの型
    val value: Any, // フィールドの値
)
