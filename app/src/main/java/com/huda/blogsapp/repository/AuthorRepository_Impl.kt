package com.huda.blogsapp.repository

import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.util.DomainMapper
import com.huda.blogsapp.network.AuthorService
import com.huda.blogsapp.network.model.AuthorDto
import com.huda.blogsapp.network.model.AuthorDtoMapper

class AuthorRepository_Impl(
    private val authorService: AuthorService,
    private val mapper: AuthorDtoMapper
) : Repository<Author> {
    override suspend fun getList(id: Int, page: Int, limit: Int): List<Author> {
        val response = authorService.getAuthorList(page, limit)
        val authors = response.body()
        return authors?.let { mapper.toDomainList(it) } ?: listOf()
    }

    override suspend fun getItem(id: Int): Author {
        return mapper.mapToDomainModel(authorService.getAuthorById(id))
    }

}