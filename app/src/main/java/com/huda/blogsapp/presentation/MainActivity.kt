package com.huda.blogsapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.GsonBuilder
import com.huda.blogsapp.R
import com.huda.blogsapp.network.AuthorService
import com.huda.blogsapp.network.CommentService
import com.huda.blogsapp.network.PostService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
            .baseUrl("https://sym-json-server.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

        val authorService = retrofit
            .create(AuthorService::class.java)
        val postService = retrofit
            .create(PostService::class.java)
        val commentService = retrofit
            .create(CommentService::class.java)

        CoroutineScope(IO).launch {
            val authorResponse = authorService.getAuthorList(
                1,10
            )
            val response = authorService.getAuthorById(
                12
            )
            Log.d(
                "MainActivity2", "onCreate: ${
                    authorResponse.headers().get("Link")
                } "
            )
             Log.d(
                "MainActivity2", "onCreate: ${
                    response.name
                } "
            )
            val post = postService.getPostById(
                12
            )
            val postsResponse = postService.getPostList(
                1,10
            )
            Log.d(
                "MainActivity2", "onCreate: ${
                    postsResponse.headers().get("Link")
                } "
            )
            Log.d(
                "MainActivity2", "onCreate: ${
                    post.title
                } "
            )
         val comment= commentService.getCommentById(
                12
            )
            val commentsResponse = commentService.getCommentsList(
                1,10
            )
            Log.d(
                "MainActivity2", "onCreate: ${
                    commentsResponse.headers().get("Link")
                } "
            )
            Log.d(
                "MainActivity2", "onCreate: ${
                    comment.date
                } "
            )
        }
    }
}