package com.huda.blogsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.utils.LoadPicture

@Composable
fun PostCard(
    post: Post,
    authorImgUrl: String,
    authorUserName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Start)
            ) {
                LoadImage(url = authorImgUrl)
                Spacer(modifier = Modifier.padding(6.dp))
                Column(modifier = Modifier.align(CenterVertically)) {
                    Text(
                        text = authorUserName,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Start)
                    )
                    Text(
                        text = post.date.toString(),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Start)
                    )
                }
            }

            Text(
                text = post.title.toString(),
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .fillMaxWidth(),
                color = Color.Black
            )
            Text(
                text = post.body.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
            if (!post.imageUrl.isNullOrEmpty()) {
                val img = post.imageUrl?.let {
                    LoadPicture(
                        url = it,
                        defaultImage = R.drawable.img_placeholder
                    ).value
                }
                if (img != null) {
                    Image(
                        bitmap = img.asImageBitmap(),
                        modifier = Modifier.fillMaxWidth(),
                        contentScale = ContentScale.Inside,
                        contentDescription = ""
                    )
                }
            }
            Text(
                text = "Comments",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
                    .align(End)
                    .padding(end = 16.dp, top = 6.dp)
                    .clickable(onClick = onClick)
            )
        }

    }
}

//@Preview
//@Composable
//fun ViewPostCard() {
//    val post = Post(
//        1, "12/8/2021",
//        "This is A Post", "I want to show this post to EVERYONE in the world",
//        "https://picsum.photos/id/374/640/480", 2
//    )
//    val authorName = "Huda Anwar"
//    val authorImgUrl = "https://i.pravatar.cc/450?u=Adan.Reinger44"
//    PostCard(
//        post = post,
//        authorImgUrl = authorImgUrl,
//        authorName = authorName,
//        onClick = {}
//    )
//}