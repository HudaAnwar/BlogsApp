package com.huda.blogsapp.presentation.ui.author_list


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.huda.blogsapp.R


class AuthorsListFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                Column {
                    Text(text = "Author List")
                    Button(onClick = {
                        findNavController().navigate(R.id.viewAuthorDetails)
                    }) {
                        Text(text = "TO DETAILS FRAGMENT")
                    }
                }
            }
        }
    }
}