package com.huda.blogsapp.network.model

import com.google.gson.annotations.SerializedName

data class AuthorDto(
    @SerializedName("id")
    var id: Int,
    @SerializedName("name")
    var name: String?,
    @SerializedName("userName")
    var userName: String?,
    @SerializedName("email")
    var email: String?,
    @SerializedName("avatarUrl")
    var imgUrl: String?,
    @SerializedName("address")
    var address: Address?,
    )
