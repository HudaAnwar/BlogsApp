package com.huda.blogsapp.presentation.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.utils.Constants
import com.huda.blogsapp.utils.Constants.PAGE_SIZE

@Composable
fun PostList(
    posts: List<Post>,
    author: Author,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onPageEnd: () -> Unit,
    loading: Boolean,
    navController: NavController
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (loading && posts.isEmpty()) {
            LoadingAuthorListShimmer(cardHeight = 250.dp, 5)
        } else {
            LazyColumn {
                itemsIndexed(posts) { index, post ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                        onPageEnd()
                    }
                    PostCard(
                        post = post,
                        authorImgUrl = author.imgUrl ?: "",
                        authorUserName = author.userName ?: "",
                    ) {
                        val bundle = Bundle()
                        bundle.putInt("id", post.id)
                        navController.navigate(R.id.viewPostComments, bundle)
                    }
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}


















