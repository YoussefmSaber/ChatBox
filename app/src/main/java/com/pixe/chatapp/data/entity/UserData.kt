package com.pixe.chatapp.data.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: String,
    @SerialName("created_at")
    val createdAt: String,
    val name: String,
    val friends: List<String>,
    val email: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    val image: String?,
    val bio: String?
)
