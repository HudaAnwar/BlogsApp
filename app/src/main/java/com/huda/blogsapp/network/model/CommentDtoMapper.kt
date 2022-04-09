package com.huda.blogsapp.network.model

import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.domain.util.DomainMapper

class CommentDtoMapper : DomainMapper<CommentDto, Comment> {
    override fun mapToDomainModel(model: CommentDto): Comment {
        with(model) {
            return Comment(
                id = id,
                date = date,
                body = body,
                userName = userName,
                email = email,
                imgUrl = imgUrl,
                postId = postId
            )
        }
    }

    override fun mapFromDomainModel(domainModel: Comment): CommentDto {
        with(domainModel) {
            return CommentDto(
                id = id,
                date = date,
                body = body,
                userName = userName,
                email = email,
                imgUrl = imgUrl,
                postId = postId
            )
        }
    }

}