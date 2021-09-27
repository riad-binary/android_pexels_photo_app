package com.riad.pexelsdemoapp.data.models

class PostResponse : ArrayList<PostResponseItem>()

data class PostResponseItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
)