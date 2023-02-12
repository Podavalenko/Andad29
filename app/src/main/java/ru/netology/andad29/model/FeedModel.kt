package ru.netology.andad29.model

import ru.netology.andad29.dto.Post

data class FeedModel(
    val posts: List<Post> = emptyList(),
    val empty: Boolean = false
)