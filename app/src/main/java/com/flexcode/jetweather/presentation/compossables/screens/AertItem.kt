package com.flexcode.jetweather.presentation.compossables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertItem(
    severity: String,
    headline: String
) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(
            text = severity,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = headline,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis
        )
    }

}