package com.flexcode.jetweather.presentation.compossables.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.flexcode.jetweather.data.models.Alert
import com.flexcode.jetweather.data.models.Alerts
import com.flexcode.jetweather.presentation.compossables.screens.home.HomeViewModel
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun AlertScreen(
    viewModel : HomeViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {


        LazyColumn(
            contentPadding = PaddingValues(8.dp),
            content = {
                val alerts : List<Alert> = state.data?.alerts?.alert ?: emptyList()
                items(alerts){ details->
                    AlertItem(
                        severity = details.severity!!,
                        headline = details.headline!!
                    )
                }
            }
        )
    }
}