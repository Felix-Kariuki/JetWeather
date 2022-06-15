package com.flexcode.jetweather.presentation.compossables.screens.home.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flexcode.jetweather.domain.model.Locations
import com.flexcode.jetweather.ui.theme.DarkBackground
import com.flexcode.jetweather.ui.theme.Gray
import com.flexcode.jetweather.ui.theme.TextWhite

@Composable
fun CityItem(
    modifier: Modifier,
    backgroundColor: Color,
    location: String,
) {

    Card(
        elevation = 5.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(start = 2.dp, end = 2.dp)
                .background(Gray),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = location,
                color = Color.White
            )

        }

    }
    /*Row(
        modifier = Modifier.padding(8.dp),
    ) {
        Text(
            text = location,
            fontWeight = FontWeight.Bold,
            color = TextWhite,
            fontSize = 16.sp
        )
    }*/
}