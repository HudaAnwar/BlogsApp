package com.huda.blogsapp.domain.model

data class Comment(
    var id: Int,
    var date: String?,
    var body: String?,
    var userName: String?,
    var email: String?,
    var imgUrl: String?,
    var postId: Int
)
