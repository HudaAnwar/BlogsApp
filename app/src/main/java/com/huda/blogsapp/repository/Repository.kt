package com.huda.blogsapp.repository

import com.huda.blogsapp.domain.model.Author

interface Repository<T> {
    suspend fun getList(page: Int, limit: Int = 10): List<T>
    suspend fun getItem(id: Int): T
}