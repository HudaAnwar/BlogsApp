package com.huda.blogsapp.presentation.ui.author_list


import android.os.Bundle
import android.util.Log
import android.util.Log.v
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.huda.blogsapp.R
import com.huda.blogsapp.presentation.components.*
import com.huda.blogsapp.utils.Constants.PAGE_SIZE
import com.huda.blogsapp.utils.Constants.TAG
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AuthorsListFragment : Fragment() {
    val viewModel by viewModels<AuthorListViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val authors = viewModel.authors.value
                val page = viewModel.page.value
                val loading = viewModel.loading.value
                Scaffold(topBar = {
                    MyAppBar("Blogs App", false, onClick = {})
                }) {
                    AuthorList(
                        loading = loading,
                        authors = authors,
                        onChangeScrollPosition = viewModel::onChangeScrollPosition,
                        page = page,
                        onPageEnd = {
                            viewModel.nextPage()
                        },
                        navController = findNavController()
                    )
                }
            }
        }
    }
}