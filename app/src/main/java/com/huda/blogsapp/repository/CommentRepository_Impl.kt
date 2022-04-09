package com.huda.blogsapp.repository

import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.network.CommentService
import com.huda.blogsapp.network.model.CommentDtoMapper

class CommentRepository_Impl(
    private val commentService: CommentService,
    private val mapper:CommentDtoMapper
):Repository<Comment> {
    override suspend fun getList(page: Int, limit: Int): List<Comment> {
        val response = commentService.getCommentsList(page, limit)
        val comments = response.body()
        return comments?.let { mapper.toDomainList(it) } ?: listOf()
    }

    override suspend fun getItem(id: Int): Comment {
       return mapper.mapToDomainModel(commentService.getCommentById(id))
    }

}