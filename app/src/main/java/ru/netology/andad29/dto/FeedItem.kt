package ru.netology.andad29.dto

sealed interface FeedItem {
    abstract val id: Long
}

data class AdItem(
    override val id: Long,
    val url: String,
    val image: String,
) : FeedItem