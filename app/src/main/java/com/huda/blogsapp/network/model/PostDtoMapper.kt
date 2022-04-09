package com.huda.blogsapp.network.model

import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.domain.util.DomainMapper

class PostDtoMapper : DomainMapper<PostDto, Post> {
    override fun mapToDomainModel(model: PostDto): Post {
        with(model) {
            return Post(
                id = id,
                date = date,
                title = title,
                body = body,
                imageUrl = imageUrl,
                authorId = authorId
            )
        }
    }

    override fun mapFromDomainModel(domainModel: Post): PostDto {
        with(domainModel) {
            return PostDto(
                id = id,
                date = date,
                title = title,
                body = body,
                imageUrl = imageUrl,
                authorId = authorId
            )
        }
    }



}