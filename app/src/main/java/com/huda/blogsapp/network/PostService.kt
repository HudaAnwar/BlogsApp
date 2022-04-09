package com.huda.blogsapp.network

import com.huda.blogsapp.network.model.AuthorDto
import com.huda.blogsapp.network.model.PostDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PostService {
    @GET("authors/{id}/posts")
    suspend fun getPostList(
        @Path("id") id: Int,
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Response<List<PostDto>>

    @GET("posts/{id}")
    suspend fun getPostById(
         @Path("id") id: Int
    ): PostDto
//
//    @GET("posts")
//    suspend fun getAuthorPosts(
//        @Query("authorId") authorId: Int,
//        @Query("_page") page: Int,
//        @Query("_limit") limit: Int
//    ): Response<List<PostDto>>

}