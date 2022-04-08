package com.huda.blogsapp.domain.model

data class Author(
    var id: Int,
    var name: String?,
    var userName: String?,
    var email: String?,
    var imgUrl: String?,
    var address: Address?,
)