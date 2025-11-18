package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.data.remote.model.BlockChildrenResponse

interface NotionRemoteDataSource {
    suspend fun getBlocks(blockId: String, pageSize: Int? = null, startCursor: String? = null): BlockChildrenResponse
}
