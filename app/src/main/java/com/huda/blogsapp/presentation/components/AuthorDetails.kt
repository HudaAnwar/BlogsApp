package com.huda.blogsapp.presentation.components

import android.content.Context
import android.graphics.drawable.shapes.Shape
import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.huda.blogsapp.R
import com.huda.blogsapp.domain.model.Author
import com.huda.blogsapp.utils.Constants
import com.huda.blogsapp.utils.LoadPicture
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*


val address = mutableStateOf("")

@Composable
fun AuthorDetails(
    loading: Boolean,
    author: Author,
    geocoder: Geocoder,
    onClick: () -> Unit
) {
    if (loading) {
        LoadingAuthorListShimmer(cardHeight = 200.dp, 1)
    } else {
        LaunchedEffect(key1 = "c1", block = {
            getAddress(author.address, geocoder)
        })
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_arrow_back_24),
                contentDescription = "back arrow",
                modifier = Modifier
                    .clickable(onClick = onClick)
                    .padding(start = 16.dp)
                    .align(Start),
                colorFilter = ColorFilter.tint(Color.Black)

            )
            val image = author.imgUrl?.let {
                LoadPicture(
                    url = it,
                    defaultImage = DEFAULT_AUTHOR_IMAGE
                ).value
            }
            image?.let { img ->
                Image(
                    bitmap = img.asImageBitmap(),
                    contentDescription = "Author image",
                    modifier = Modifier
                        .size(80.dp)
                        .align(CenterHorizontally)
                        .clip(CircleShape)
                        .border(1.0.dp, Color.Black, CircleShape)
                )
            }
            Spacer(modifier = Modifier.padding(4.dp))
            Row(
                modifier = Modifier.align(CenterHorizontally)
            ) {
                Text(
                    text = "${author.name}",
                    modifier = Modifier.align(CenterVertically),
                    color = Color.Black,
                    style = MaterialTheme.typography.h6
                )
                Text(
                    text = " (${author.userName})",
                    modifier = Modifier.align(CenterVertically),
                    color = Color.Gray,
                    style = MaterialTheme.typography.body2
                )
            }

            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = author.email ?: "",
                modifier = Modifier.align(CenterHorizontally),
                color = Color.Black,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.padding(4.dp))
            Text(
                text = address.value,
                modifier = Modifier.align(CenterHorizontally),
                color = Color.Blue,
                style = MaterialTheme.typography.body1
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF2F2F2)),
            ) {
                Text(
                    text = "Posts",
                    modifier = Modifier
                        .align(CenterVertically)
                        .padding(8.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.body1,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
    CircularIndeterminateProgressBar(isDisplayed = loading)
}

suspend fun getAddress(addressCoordinates: List<Double>, geocoder: Geocoder) {
    withContext(Dispatchers.IO) {
        var addressList: List<Address?>?
        try {
            addressList = geocoder.getFromLocation(
                addressCoordinates[0],
                addressCoordinates[1],
                1
            )
            if ((addressList != null && addressList.isNotEmpty())) {
                val fullAddress = addressList[0]
                val sb = StringBuilder()
                for (i in 0 until fullAddress?.maxAddressLineIndex!!) {
                    sb.append(fullAddress.getAddressLine(i)).append(", ")
                }
                sb.append(fullAddress.countryName)
                address.value = sb.toString()
            }
            Log.i(Constants.TAG, "Fetch address list$addressList")
        } catch (e: IOException) {
            Log.e(Constants.TAG, "Failed Fetched Address List")
        }
    }
}
