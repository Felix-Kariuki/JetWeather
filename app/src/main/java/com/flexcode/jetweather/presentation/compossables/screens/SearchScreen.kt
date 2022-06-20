package com.flexcode.jetweather.presentation.compossables.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import timber.log.Timber

@Destination
@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state.value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {

        SearchBar(
            viewModel = viewModel,
            modifier = Modifier
                .fillMaxWidth()
                .height(67.dp)
                .padding(8.dp),
            onSearch = {
                viewModel.search(it)
            }
        )


        Timber.i("Results: ${state.data}")

    }
}

@Composable
fun SearchBar(
    viewModel: SearchViewModel,
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val query = viewModel.query.value

    TextField(
        value = query,
        onValueChange = {
            viewModel.setSearchQuery(it)
        },
        placeholder = {
            Text(
                text = "Enter city...",
                color = Color.LightGray
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .shadow(4.dp, CircleShape)
            .background(Color.Transparent, CircleShape),
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions(
            capitalization = KeyboardCapitalization.Words,
            autoCorrect = true,
            keyboardType = KeyboardType.Text,
        ),
        textStyle = TextStyle(color = Color.White),
        maxLines = 1,
        singleLine = true,
        trailingIcon = {
            IconButton(onClick = { onSearch(query) }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = Color.LightGray,
                    contentDescription = null
                )
            }
        }
    )
}