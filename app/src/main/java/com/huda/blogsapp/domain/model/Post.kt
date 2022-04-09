package com.huda.blogsapp.domain.model

data class Post(
    var id: Int,
    var date: String?,
    var title: String?,
    var body: String?,
    var imageUrl: String?,
    var authorId: Int
)