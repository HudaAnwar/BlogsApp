package com.huda.blogsapp.presentation.ui.author_list

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.repository.Repository
import com.huda.blogsapp.utils.Constants.PAGE_SIZE
import com.huda.blogsapp.utils.Constants.TAG
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthorListViewModel @Inject constructor(
    private val repository: Repository<Author>
) : ViewModel() {
    val authors = mutableStateOf<List<Author>>(listOf())
    val page = mutableStateOf(1)
    val loading = mutableStateOf(false)
    private var scrollPosition = 0

    init {
        getAuthorsList()
    }

    fun getAuthorsList() {
        viewModelScope.launch {
            loading.value = true
            val authorsResult = repository.getList(page = 1, limit = PAGE_SIZE)
            authors.value = authorsResult
            loading.value = false
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            if ((scrollPosition + 1) >= (page.value * PAGE_SIZE)) {
                loading.value = true
                incrementPage()
                Log.d(TAG, "nextPage: ${page.value}")
//                delay(1000)
                if (page.value > 1) {
                    val result = repository
                        .getList(page = page.value, limit = PAGE_SIZE)
                    Log.d(TAG, "nextPage: $result")
                    appendAuthors(result)
                }
                loading.value = false
            }
        }
    }

    private fun appendAuthors(authors: List<Author>) {
        val current = ArrayList(this.authors.value)
        current.addAll(authors)
        this.authors.value = current
    }

    private fun incrementPage() {
        page.value = page.value + 1
    }

//    private fun resetSearchState() {
//        authors.value = listOf()
//        page.value = 1
//        onChangeScrollPosition(0)
//    }


    fun onChangeScrollPosition(position: Int) {
        scrollPosition = position
    }
}