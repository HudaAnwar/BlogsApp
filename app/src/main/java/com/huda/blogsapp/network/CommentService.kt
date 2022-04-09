package com.huda.blogsapp.network

import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.network.model.AuthorDto
import com.huda.blogsapp.network.model.CommentDto
import com.huda.blogsapp.network.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentService {
    @GET("comments")
    suspend fun getCommentsList(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Response<List<CommentDto>>

    @GET("comments/{id}")
    suspend fun getCommentById(
        @Path("id") id: Int
    ): CommentDto
}