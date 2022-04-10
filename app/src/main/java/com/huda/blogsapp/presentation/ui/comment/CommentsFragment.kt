package com.huda.blogsapp.presentation.ui.comment

import android.location.Geocoder
import android.os.Bundle
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
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.huda.blogsapp.R
import com.huda.blogsapp.presentation.components.*
import com.huda.blogsapp.presentation.ui.author.AuthorDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CommentsFragment : Fragment() {

    private val viewModel by viewModels<CommentsViewModel>()
    private var postId = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("id")?.let { pId ->
            postId = pId
            viewModel.getCommentsList(postId)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val comments = viewModel.comments.value
                val loading = viewModel.loading.value
                val page = viewModel.page.value
                Scaffold(topBar = {
                    MyAppBar(title = "Comments", showBackButton = true) {
                        findNavController().popBackStack()
                    }
                }) {
                    CommentsList(
                        comments = comments,
                        onPageEnd = { viewModel.nextPage(postId) },
                        loading = loading,
                        onChangeScrollPosition = viewModel::onChangeScrollPosition,
                        page = page
                    )
                }
            }
        }
    }
}