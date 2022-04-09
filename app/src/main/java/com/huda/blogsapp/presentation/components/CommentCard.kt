package com.huda.blogsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Comment
import com.huda.blogsapp.domain.model.Post
import com.huda.blogsapp.utils.LoadPicture

@Composable
fun CommentCard(comment: Comment) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .align(Alignment.Start)
            ) {
                comment.imgUrl?.let { LoadImage(url = it) }
                Spacer(modifier = Modifier.padding(6.dp))
                Column(modifier = Modifier.align(Alignment.CenterVertically)) {
                    Text(
                        text = comment.userName ?: "",
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = comment.email.toString(),
                        color = Color.Black,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                    Text(
                        text = comment.date.toString(),
                        color = Color.Gray,
                        style = MaterialTheme.typography.body2,
                        modifier = Modifier.align(Alignment.Start)
                    )
                }
            }
            Text(
                text = comment.body.toString(),
                style = MaterialTheme.typography.body1,
                color = Color.Black,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 6.dp)
            )
//            if (!comment.imgUrl.isNullOrEmpty()) {
//                val img = comment.imgUrl?.let {
//                    LoadPicture(
//                        url = it,
//                        defaultImage = R.drawable.img_placeholder
//                    ).value
//                }
//                if (img != null) {
//                    Image(
//                        bitmap = img.asImageBitmap(),
//                        modifier = Modifier.fillMaxWidth(),
//                        contentScale = ContentScale.Inside,
//                        contentDescription = ""
//                    )
//                }
//            }

        }
    }
}

//
//@Preview
//@Composable
//fun ViewCommntCard() {
//    val comment = Comment(
//        1, "12/8/2021",
//        "This is A Post", "huda.anwar94",
//        "huda.anwar94@gmail.com",
//        "https://picsum.photos/id/374/640/480", 2
//    )
//    CommentCard(
//        comment = comment
//    )
//}