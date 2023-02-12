package ru.netology.andad29.model

data class FeedModelState(
    val loading: Boolean = false,
    val error: Boolean = false,
    val refresh: Boolean = false
)