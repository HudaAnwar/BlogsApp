package com.huda.blogsapp.network.model

import com.google.gson.annotations.SerializedName

data class CommentDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("date")
    var date: String?,
    @SerializedName("body")
    var body: String?,
    @SerializedName("userName")
    var userName: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("avatarUrl")
    var imgUrl: String?,
    @SerializedName("postId")
    var postId: Int
)