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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.jetweather.ui.theme.Gray

@Composable
fun DetailsItem(
    text1: String,
    textValue: String
) {
    Card(
        modifier = Modifier
            .size(width = 110.dp, height = 90.dp)
            .padding(5.dp),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Gray,
        elevation = 5.dp
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Text(
                text = text1,
                fontSize = 15.sp,
                color = Color.White
            )
            Text(
                text = textValue,
                fontSize = 16.sp,
                color = Color.White
            )
        }
    }
}
