package com.huda.blogsapp.repository

import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.network.PostService
import com.huda.blogsapp.network.model.PostDtoMapper

class PostRepository_Impl(
    private val postService: PostService,
    private val mapper: PostDtoMapper
):Repository<Post> {
    override suspend fun getList(page: Int, limit: Int): List<Post> {
        val response = postService.getPostList(page, limit)
        val posts = response.body()
        return posts?.let { mapper.toDomainList(it) } ?: listOf()
    }

    override suspend fun getItem(id: Int): Post {
       return mapper.mapToDomainModel(postService.getPostById(id))
    }
}