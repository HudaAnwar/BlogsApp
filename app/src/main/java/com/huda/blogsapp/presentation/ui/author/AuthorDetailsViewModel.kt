package com.huda.blogsapp.presentation.ui.author

import android.location.Geocoder
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.repository.PostRepository_Impl
import com.huda.blogsapp.repository.Repository
import com.huda.blogsapp.utils.Constants
import com.huda.blogsapp.utils.Constants.PAGE_SIZE
import com.huda.blogsapp.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import java.io.IOException
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AuthorDetailsViewModel @Inject constructor(
    private val authorRepository: Repository<Author>,
    private val postRepository: Repository<Post>,
) : ViewModel() {
    val posts = mutableStateOf<List<Post>>(listOf())
    val addressState = mutableStateOf("")
    val author = mutableStateOf(
        Author(-1, "", "", "", "", listOf())
    )
    val loading = mutableStateOf(false)
    val loading2 = mutableStateOf(false)
    val page = mutableStateOf(1)
    private var scrollPosition = 0

    fun getAuthorById(authorId: Int) {
        viewModelScope.launch {
            loading.value = true
            val res = authorRepository.getItem(authorId)
            author.value = res
            loading.value = false
        }
    }

    fun getPostsList(authorId: Int) {
        viewModelScope.launch {
            loading2.value = true
            val res = postRepository
                .getList(authorId, page.value, PAGE_SIZE)
            posts.value = res
            loading2.value = false
        }
    }

    fun nextPage(authorId: Int) {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                loading2.value = true
                incrementPage()
                Log.d(TAG, "nextPage: ${page.value}")
                if (page.value > 1) {
                    val result = postRepository
                        .getList(authorId, page.value, PAGE_SIZE)
                    Log.d(TAG, "nextPage: $result")
                    appendPosts(result)
                }
                loading2.value = false
            }
        }
    }

    private fun appendPosts(posts: List<Post>) {
        val current = ArrayList(this.posts.value)
        current.addAll(posts)
        this.posts.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }
}