package com.example.todoAppRemake.data.remote.model

import com.example.todoAppRemake.data.remote.deserializer.BlockDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class BlockDeserializationTest {

    private lateinit var gson: Gson

    @Before
    fun setup() {
        gson = GsonBuilder()
            .registerTypeAdapter(Block::class.java, BlockDeserializer())
            .create()
    }

    @Test
    fun `heading_1ブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-h1",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "heading_1",
                "heading_1": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Heading 1 Title", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Heading 1 Title",
                            "href": null
                        }
                    ],
                    "is_toggleable": false,
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Heading1)
        val heading1Block = block as Block.Heading1
        assertEquals("block-id-h1", heading1Block.id)
        assertEquals("heading_1", heading1Block.type)
        assertEquals("Heading 1 Title", heading1Block.content.richText[0].plainText)
    }

    @Test
    fun `heading_2ブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-h2",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "heading_2",
                "heading_2": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Heading 2 Title", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Heading 2 Title",
                            "href": null
                        }
                    ],
                    "is_toggleable": false,
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Heading2)
        val heading2Block = block as Block.Heading2
        assertEquals("block-id-h2", heading2Block.id)
        assertEquals("heading_2", heading2Block.type)
        assertEquals("Heading 2 Title", heading2Block.content.richText[0].plainText)
    }

    @Test
    fun `heading_3ブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-h3",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "heading_3",
                "heading_3": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Heading 3 Title", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Heading 3 Title",
                            "href": null
                        }
                    ],
                    "is_toggleable": false,
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Heading3)
        val heading3Block = block as Block.Heading3
        assertEquals("block-id-h3", heading3Block.id)
        assertEquals("heading_3", heading3Block.type)
        assertEquals("Heading 3 Title", heading3Block.content.richText[0].plainText)
    }

    @Test
    fun `paragraphブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-para",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "paragraph",
                "paragraph": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "This is a paragraph.", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "This is a paragraph.",
                            "href": null
                        }
                    ],
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Paragraph)
        val paragraphBlock = block as Block.Paragraph
        assertEquals("block-id-para", paragraphBlock.id)
        assertEquals("paragraph", paragraphBlock.type)
        assertEquals("This is a paragraph.", paragraphBlock.content.richText[0].plainText)
    }

    @Test
    fun `to_doブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-todo",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "to_do",
                "to_do": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Buy groceries", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Buy groceries",
                            "href": null
                        }
                    ],
                    "checked": true,
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.ToDo)
        val todoBlock = block as Block.ToDo
        assertEquals("block-id-todo", todoBlock.id)
        assertEquals("to_do", todoBlock.type)
        assertEquals("Buy groceries", todoBlock.content.richText[0].plainText)
        assertTrue(todoBlock.content.checked)
    }

    @Test
    fun `bulleted_list_itemブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-bullet",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "bulleted_list_item",
                "bulleted_list_item": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Bullet point 1", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Bullet point 1",
                            "href": null
                        }
                    ],
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.BulletedListItem)
        val bulletedListItemBlock = block as Block.BulletedListItem
        assertEquals("block-id-bullet", bulletedListItemBlock.id)
        assertEquals("bulleted_list_item", bulletedListItemBlock.type)
        assertEquals("Bullet point 1", bulletedListItemBlock.content.richText[0].plainText)
    }

    @Test
    fun `toggleブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-toggle",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "toggle",
                "toggle": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Toggle item", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Toggle item",
                            "href": null
                        }
                    ],
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Toggle)
        val toggleBlock = block as Block.Toggle
        assertEquals("block-id-toggle", toggleBlock.id)
        assertEquals("toggle", toggleBlock.type)
        assertEquals("Toggle item", toggleBlock.content.richText[0].plainText)
    }

    @Test
    fun `numbered_list_itemブロックが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-numlist",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "numbered_list_item",
                "numbered_list_item": {
                    "rich_text": [
                        {
                            "type": "text",
                            "text": { "content": "Numbered item 1", "link": null },
                            "annotations": { "bold": false, "italic": false, "strikethrough": false, "underline": false, "code": false, "color": "default" },
                            "plain_text": "Numbered item 1",
                            "href": null
                        }
                    ],
                    "color": "default"
                }
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.NumberedListItem)
        val numberedListItemBlock = block as Block.NumberedListItem
        assertEquals("block-id-numlist", numberedListItemBlock.id)
        assertEquals("numbered_list_item", numberedListItemBlock.type)
        assertEquals("Numbered item 1", numberedListItemBlock.content.richText[0].plainText)
    }

    @Test
    fun `サポートされていないブロックタイプが正しくデシリアライズされること`() {
        val json = """
            {
                "object": "block",
                "id": "block-id-unsupported",
                "parent": { "type": "page_id", "page_id": "page-id" },
                "created_time": "2023-01-01T00:00:00.000Z",
                "last_edited_time": "2023-01-01T00:00:00.000Z",
                "created_by": { "object": "user", "id": "user-id" },
                "last_edited_by": { "object": "user", "id": "user-id" },
                "has_children": false,
                "archived": false,
                "type": "unsupported_type",
                "unsupported_type": {} 
            }
        """.trimIndent()

        val block = gson.fromJson(json, Block::class.java)

        assertTrue(block is Block.Unsupported)
        val unsupportedBlock = block as Block.Unsupported
        assertEquals("block-id-unsupported", unsupportedBlock.id)
        assertEquals("unsupported_type", unsupportedBlock.type)
    }
}
