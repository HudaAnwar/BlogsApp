package com.huda.blogsapp.presentation.components

import android.os.Bundle
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.utils.Constants


@Composable
fun CommentsList(
    comments: List<Comment>,
    onChangeScrollPosition: (Int) -> Unit,
    page: Int,
    onPageEnd: () -> Unit,
    loading: Boolean
) {

    Column {
        if (loading && comments.isEmpty()) {
            LoadingAuthorListShimmer(cardHeight = 250.dp, 5)
        } else {
            LazyColumn() {
                itemsIndexed(comments) { index, comment ->
//                    if (hasNext) {
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * Constants.PAGE_SIZE) && !loading) {
                        onPageEnd()
                    }
//                    }
                    CommentCard(comment = comment)
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}