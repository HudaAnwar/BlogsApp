package com.huda.blogsapp.domain.model

import com.huda.blogsapp.network.model.Address

data class Author(
    var id: Int,
    var name: String?,
    var userName: String?,
    var email: String?,
    var imgUrl: String?,
    var address: List<Double>,
)