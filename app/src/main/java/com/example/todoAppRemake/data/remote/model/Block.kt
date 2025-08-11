
package com.example.todoAppRemake.data.remote.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

// v1/blocks/{block_id}/children のレスポンス
data class BlockChildrenResponse(
    @SerializedName("object") val objectType: String,
    val results: List<Block>,
    @SerializedName("next_cursor") val nextCursor: String?,
    @SerializedName("has_more") val hasMore: Boolean,
    val type: String,
    @SerializedName("page_or_database") val pageOrDatabase: JsonObject,
)

// Block Sealed Class
sealed class Block {
    abstract val id: String
    abstract val type: String
    abstract val hasChildren: Boolean

    data class Heading1(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("heading_1") val content: HeadingContent,
    ) : Block()

    data class Heading2(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("heading_2") val content: HeadingContent,
    ) : Block()

    data class Heading3(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("heading_3") val content: HeadingContent,
    ) : Block()

    data class Paragraph(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("paragraph") val content: ParagraphContent,
    ) : Block()

    data class ToDo(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("to_do") val content: ToDoContent,
    ) : Block()

    data class Toggle(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("toggle") val content: ToggleContent,
    ) : Block()

    data class BulletedListItem(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
        @SerializedName("bulleted_list_item") val content: ListItemContent,
    ) : Block()

    data class Unsupported(
        override val id: String,
        override val type: String,
        override val hasChildren: Boolean,
    ) : Block()
}

// --- ブロックのコンテンツ詳細 ---

data class HeadingContent(
    @SerializedName("rich_text") val richText: List<RichText>,
    @SerializedName("is_toggleable") val isToggleable: Boolean,
    val color: String,
)

data class ParagraphContent(
    @SerializedName("rich_text") val richText: List<RichText>,
    val color: String,
)

data class ToDoContent(
    @SerializedName("rich_text") val richText: List<RichText>,
    val checked: Boolean,
    val color: String,
)

data class ToggleContent(
    @SerializedName("rich_text") val richText: List<RichText>,
    val color: String,
)

data class ListItemContent(
    @SerializedName("rich_text") val richText: List<RichText>,
    val color: String,
)

// --- 共通のデータ構造 ---

data class Parent(
    val type: String,
    @SerializedName("page_id") val pageId: String,
)

data class User(
    @SerializedName("object") val objectType: String,
    val id: String,
)

data class RichText(
    val type: String,
    val text: TextContent,
    val annotations: Annotations,
    @SerializedName("plain_text") val plainText: String,
    val href: String?,
)

data class TextContent(
    val content: String,
    val link: String?,
)

data class Annotations(
    val bold: Boolean,
    val italic: Boolean,
    val strikethrough: Boolean,
    val underline: Boolean,
    val code: Boolean,
    val color: String,
)
