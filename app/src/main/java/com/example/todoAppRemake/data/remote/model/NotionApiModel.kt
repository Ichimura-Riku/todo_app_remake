package com.example.todoAppRemake.data.remote.model

import com.example.todoAppRemake.domain.model.NotionPage

data class NotionQueryRequest(
    val databaseId: String,
    val filter: Map<String, Any>? = null,
    val sorts: List<Map<String, Any>>? = null,
)

data class NotionQueryResponse(
    val results: List<NotionPage>,
)

data class NotionCreatePageRequest(
    val parent: Map<String, String>,
    val properties: Map<String, Any>,
)
