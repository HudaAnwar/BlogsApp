package com.huda.blogsapp.presentation.ui.author

import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.huda.blogsapp.R
import com.huda.blogsapp.presentation.components.AuthorDetails
import com.huda.blogsapp.presentation.components.CircularIndeterminateProgressBar
import com.huda.blogsapp.presentation.components.LoadingAuthorListShimmer
import com.huda.blogsapp.presentation.components.PostList
import com.huda.blogsapp.utils.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

@AndroidEntryPoint
class AuthorDetailsFragment : Fragment() {

    private var authorId = -1
    private val viewModel by viewModels<AuthorDetailsViewModel>()
    private lateinit var geocoder: Geocoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("id")?.let { rId ->
            authorId = rId
            viewModel.getAuthorById(authorId)
            viewModel.getPostsList(authorId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                geocoder = Geocoder(
                    context,
                    Locale.ENGLISH
                )
                val author = viewModel.author.value
                val loading = viewModel.loading.value
                val loading2 = viewModel.loading2.value
                val posts = viewModel.posts.value
                val page = viewModel.page.value
                Column(
                    modifier = Modifier
                        .scrollable(
                            state = rememberScrollState(),
                            orientation = Orientation.Vertical,
                            enabled = true
                        )
                        .fillMaxSize()
                ) {
                    if (loading) {
                        LoadingAuthorListShimmer(cardHeight = 200.dp, 1)
                    } else {
                        AuthorDetails(
                            author = author,
                            geocoder = geocoder,
                            onClick = {
                                findNavController().popBackStack()
                            }
                        )
                        Spacer(modifier = Modifier.padding(4.dp))
                        PostList(
                            posts = posts,
                            author = author,
                            navController = findNavController(),
                            onPageEnd = { viewModel.nextPage(authorId) },
                            loading = loading2,
                            onChangeScrollPosition = viewModel::onChangeScrollPosition,
                            page = page
                        )
                    }
                    CircularIndeterminateProgressBar(isDisplayed = loading)
                }
            }
        }
    }
}