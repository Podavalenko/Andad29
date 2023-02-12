package ru.netology.andad29.dto

data class Attachment (
    val url: String,
    val type: AttachmentType
        )

enum class AttachmentType {
    IMAGE
}