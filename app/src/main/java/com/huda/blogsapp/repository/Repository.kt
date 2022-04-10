package com.huda.blogsapp.repository

import com.huda.blogsapp.domain.model.Author

interface Repository<T> {
    suspend fun getList(id: Int = -1, page: Int, limit: Int): List<T>
    suspend fun getItem(id: Int): T
}