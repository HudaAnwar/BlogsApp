package com.huda.blogsapp.network.model

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("date")
    var date: String?,
    @SerializedName("title")
    var title: String?,
    @SerializedName("body")
    var body: String?,
    @SerializedName("imageUrl")
    var imageUrl: String?,
    @SerializedName("authorId")
    var authorId: Int
)