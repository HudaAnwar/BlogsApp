package com.huda.blogsapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.huda.blogsapp.R

@Composable
fun MyAppBar(
    title: String,
    showBackButton: Boolean,
    onClick: () -> Unit
) {
    Surface(
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(
                    color = MaterialTheme.colors.primaryVariant,
                ),
            horizontalArrangement = Arrangement.Start
        ) {
            if (showBackButton) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                    contentDescription = "back arrow",
                    modifier = Modifier
                        .clickable(onClick = onClick)
                        .padding(horizontal = 16.dp)
                        .align(CenterVertically)
                      ,
                    colorFilter = ColorFilter.tint(Color.White)
                )
            }
            Text(
                text = title,
                color = Color.White,
                style = MaterialTheme.typography.h6,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(CenterVertically)
            )
        }
    }
}