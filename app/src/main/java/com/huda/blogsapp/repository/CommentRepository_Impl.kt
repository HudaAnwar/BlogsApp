package com.huda.blogsapp.repository

import androidx.compose.runtime.mutableStateOf
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.network.CommentService
import com.huda.blogsapp.network.model.CommentDtoMapper

class CommentRepository_Impl(
    private val commentService: CommentService,
    private val mapper: CommentDtoMapper
) : Repository<Comment> {

//    val hasNext = mutableStateOf(false)
    override suspend fun getList(id: Int, page: Int, limit: Int): List<Comment> {
        val response = commentService.getCommentsList(id, page, limit)
        val comments = response.body()
//        hasNext.value = response.headers().get("Link")?.contains("rel=\"next\"") == true
        return comments?.let { mapper.toDomainList(it) } ?: listOf()
    }


    override suspend fun getItem(id: Int): Comment {
        return mapper.mapToDomainModel(commentService.getCommentById(id))
    }

}