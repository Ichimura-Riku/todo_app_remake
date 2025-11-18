package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.data.remote.model.BlockChildrenResponse
import javax.inject.Inject

class NotionRemoteDataSourceImpl
@Inject
constructor(
    private val notionApiService: NotionApiService,
) : NotionRemoteDataSource {
    override suspend fun getBlocks(
        blockId: String,
        pageSize: Int?,
        startCursor: String?,
    ): BlockChildrenResponse = try {
        notionApiService.getBlocks(
            blockId = blockId,
            pageSize = pageSize,
            startCursor = startCursor,
        )
    } catch (e: Exception) {
        // ここでエラーログを出力したり、カスタム例外に変換して上位層に伝えたりします。
        // 例: Log.e("NotionRemoteDataSource", "Error fetching blocks: ${e.message}", e)
        throw RuntimeException("Failed to fetch blocks from Notion API", e)
    }
}
