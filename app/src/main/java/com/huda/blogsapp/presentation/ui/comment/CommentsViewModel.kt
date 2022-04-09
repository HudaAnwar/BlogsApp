package com.huda.blogsapp.presentation.ui.comment

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.repository.CommentRepository_Impl
import com.huda.blogsapp.repository.Repository
import com.huda.blogsapp.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.ArrayList
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val commentsRepository: Repository<Comment>
) : ViewModel() {
    val comments = mutableStateOf<List<Comment>>(listOf())
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
//    val hasNext = mutableStateOf(false)
    private var scrollPosition = 0

    fun getCommentsList(postId: Int) {
        viewModelScope.launch {
            loading.value = true
            val res = commentsRepository
                .getList(postId, page.value, Constants.PAGE_SIZE)
            comments.value = res
//            hasNext.value = (commentsRepository as CommentRepository_Impl).hasNext.value
            loading.value = false
        }
    }

    fun nextPage(postId: Int) {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (page.value * Constants.PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(Constants.TAG, "nextPage: ${page.value}")
                if (page.value > 1) {
                    val result = commentsRepository
                        .getList(postId, page.value, Constants.PAGE_SIZE)
//                    hasNext.value = (commentsRepository as CommentRepository_Impl).hasNext.value
                    Log.d(Constants.TAG, "nextPage: $result")
                    appendComments(result)
                }
                loading.value = false
            }
        }
    }

    private fun appendComments(comments: List<Comment>) {
        val current = ArrayList(this.comments.value)
        current.addAll(comments)
        this.comments.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }
}