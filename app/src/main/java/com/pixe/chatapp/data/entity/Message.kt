package com.pixe.chatapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    @SerialName("created_at")
    val createdAt: String,
    val text: String?,
    val image: String?,
    val sender: String,
    val receiver: String,
    val importance: String = "meh"
)
