package com.huda.blogsapp.presentation.components

import android.os.Bundle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.utils.Constants

@Composable
fun AuthorList(
    loading:Boolean,
    authors:List<Author>,
    onChangeScrollPosition:(Int)->Unit,
    page:Int,
    onPageEnd:()->Unit,
    navController: NavController
){
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        if (loading && authors.isEmpty()) {
            LoadingAuthorListShimmer(cardHeight = 92.dp, itemsCount = 15)
        } else {
            LazyColumn {
                itemsIndexed(authors) { index, author ->
                    onChangeScrollPosition(index)
                    if ((index + 1) >= (page * Constants.PAGE_SIZE) && !loading) {
                        onPageEnd()
                    }
                    AuthorCard(
                        imgUrl = author.imgUrl ?: "",
                        name = author.name ?: "",
                        onClick = {
                            val bundle = Bundle()
                            bundle.putInt("id",author.id)
                            navController.navigate(R.id.viewAuthorDetails,bundle)
                        }
                    )
                }
            }
        }
        CircularIndeterminateProgressBar(isDisplayed = loading)
    }
}