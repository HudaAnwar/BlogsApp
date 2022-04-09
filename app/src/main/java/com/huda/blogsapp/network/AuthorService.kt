package com.huda.blogsapp.network

import com.huda.blogsapp.network.model.AuthorDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthorService {
    @GET("authors")
    suspend fun getAuthorList(
        @Query("_page") page: Int,
        @Query("_limit") limit: Int
    ): Response<List<AuthorDto>>

    @GET("authors/{id}")
    suspend fun getAuthorById(
         @Path("id") id: Int
    ): AuthorDto
}