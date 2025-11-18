package com.example.todoAppRemake.data.remote

import com.example.todoAppRemake.data.remote.model.Block
import com.example.todoAppRemake.data.remote.model.BlockChildrenResponse
import com.example.todoAppRemake.data.remote.model.ParagraphContent
import com.google.gson.JsonObject
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NotionRemoteDataSourceImplTest {

    private lateinit var notionApiService: NotionApiService
    private lateinit var notionRemoteDataSource: NotionRemoteDataSourceImpl

    @Before
    fun setup() {
        notionApiService = mockk()
        notionRemoteDataSource = NotionRemoteDataSourceImpl(notionApiService)
    }

    @Test
    fun `getBlocksがNotionApiServiceを呼び出し正しいレスポンスを返すこと`() = runTest {
        // Given
        val blockId = "test_block_id"
        val pageSize = 10
        val startCursor = "test_cursor"

        val mockBlock = Block.Paragraph(
            id = "block-id-para",
            type = "paragraph",
            hasChildren = false,
            content = ParagraphContent(
                richText = emptyList(),
                color = "default",
            ),
        )
        val expectedResponse = BlockChildrenResponse(
            objectType = "list",
            results = listOf(mockBlock),
            nextCursor = null,
            hasMore = false,
            type = "block",
            pageOrDatabase = JsonObject(),
        )

        coEvery {
            notionApiService.getBlocks(
                blockId = blockId,
                pageSize = pageSize,
                startCursor = startCursor,
            )
        } returns expectedResponse

        // When
        val actualResponse = notionRemoteDataSource.getBlocks(blockId, pageSize, startCursor)

        // Then
        assertEquals(expectedResponse, actualResponse)
        assertTrue(actualResponse.results.first() is Block.Paragraph)
    }

    @Test(expected = RuntimeException::class)
    fun `getBlocksでAPIエラーが発生した場合にRuntimeExceptionをスローすること`() = runBlocking {
        // Given
        val blockId = "test_block_id"
        val pageSize = 10
        val startCursor = "test_cursor"

        coEvery {
            notionApiService.getBlocks(
                blockId = blockId,
                pageSize = pageSize,
                startCursor = startCursor,
            )
        } throws RuntimeException("API Error")

        // When
        notionRemoteDataSource.getBlocks(blockId, pageSize, startCursor)

        // Then: RuntimeExceptionがスローされることを期待
    }
}
