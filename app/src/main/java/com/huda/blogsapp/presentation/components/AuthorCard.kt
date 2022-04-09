package com.huda.blogsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.huda.blogsapp.R
import com.huda.blogsapp.utils.LoadPicture

const val DEFAULT_AUTHOR_IMAGE = R.drawable.person_placeholder

@Composable
fun AuthorCard(
    imgUrl: String,
    name: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 16.dp)
            .clickable(onClick = onClick),
        shape = MaterialTheme.shapes.large,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier.padding(8.dp)
        ) {
            LoadImage(url = imgUrl)
            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = name,
                color = Color.Black,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.align(CenterVertically),
            )
        }
    }
}

@Composable
fun LoadImage(url: String) {
    val image = LoadPicture(
        url = url,
        defaultImage = DEFAULT_AUTHOR_IMAGE
    ).value
    image?.let { img ->
        Image(
            bitmap = img.asImageBitmap(),
            contentDescription = "Author image",
            modifier = Modifier
                .size(70.dp)
                .clip(CircleShape)
                .border(0.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
    }
}

//@Preview
//@Composable
//fun ViewCard() {
//    val url = "https://i.pravatar.cc/450?u=Verna33"
//    val name = "Verna33"
//    AuthorCard(imgUrl = url, name = name, onClick = {})
//}