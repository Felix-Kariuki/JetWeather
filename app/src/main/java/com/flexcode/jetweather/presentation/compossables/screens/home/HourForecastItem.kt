package com.flexcode.jetweather.presentation.compossables.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexcode.jetweather.ui.theme.DarkBackground
import com.flexcode.jetweather.ui.theme.Gray
import com.flexcode.jetweather.utils.formatTime

@Composable
fun HourItem(
    icon: String,
    time: String,
    degrees: Float
) {
    Card(
        modifier = Modifier
            .size(width = 90.dp, height = 140.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = DarkBackground,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = formatTime(time),
                fontSize = 12.sp,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(icon)
                    .crossfade(true)
                    .build(),
                contentDescription = "hour forecast $time,",
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.CenterHorizontally),
                alignment = Alignment.Center
            )
            Text(
                text = "${degrees}${0x00B0.toChar()}",
                fontSize = 12.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}