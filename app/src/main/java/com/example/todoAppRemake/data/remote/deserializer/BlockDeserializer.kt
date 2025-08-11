package com.example.todoAppRemake.data.remote.deserializer

import com.example.todoAppRemake.data.remote.model.Block
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonParseException
import java.lang.reflect.Type

class BlockDeserializer : JsonDeserializer<Block> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?,
    ): Block {
        val jsonObject = json?.asJsonObject ?: throw JsonParseException("Invalid JSON: null or not an object")
        val type = jsonObject["type"]?.asString ?: throw JsonParseException("Missing 'type' field in Block JSON")

        val deserializationContext = context ?: throw IllegalStateException("JsonDeserializationContext cannot be null")

        return when (type) {
            "heading_1" -> deserializationContext.deserialize(jsonObject, Block.Heading1::class.java)
            "heading_2" -> deserializationContext.deserialize(jsonObject, Block.Heading2::class.java)
            "heading_3" -> deserializationContext.deserialize(jsonObject, Block.Heading3::class.java)
            "paragraph" -> deserializationContext.deserialize(jsonObject, Block.Paragraph::class.java)
            "to_do" -> deserializationContext.deserialize(jsonObject, Block.ToDo::class.java)
            "toggle" -> deserializationContext.deserialize(jsonObject, Block.Toggle::class.java)
            "bulleted_list_item" -> deserializationContext.deserialize(jsonObject, Block.BulletedListItem::class.java)
            "numbered_list_item" -> deserializationContext.deserialize(jsonObject, Block.NumberedListItem::class.java)
            // Add other block types as needed
            else -> {
                // Log or handle unsupported types gracefully
                println("Unsupported block type: $type")
                deserializationContext.deserialize(jsonObject, Block.Unsupported::class.java)
            }
        }
    }
}
