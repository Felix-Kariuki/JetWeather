package com.flexcode.jetweather.presentation.compossables.screens.home

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.flexcode.jetweather.data.models.Forecastday
import com.flexcode.jetweather.domain.model.Locations
import com.flexcode.jetweather.presentation.compossables.screens.home.components.CityItem
import com.flexcode.jetweather.presentation.compossables.screens.home.components.DailyItem
import com.flexcode.jetweather.ui.theme.Blue
import com.flexcode.jetweather.ui.theme.DarkBackground
import com.flexcode.jetweather.ui.theme.Gray
import com.flexcode.jetweather.utils.dayOfTheWeek
import com.hitanshudhawan.circularprogressbar.CircularProgressBar
import com.ramcosta.composedestinations.annotation.Destination


@Destination(start = true)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    val context = LocalContext.current
    val locationDialog = remember { mutableStateOf(false) }
    val localLocations : List<Locations> = viewModel.allLocations.observeAsState().value ?: emptyList()


    /**
     * Remember to implement on long click to delete a city
     */

    if (state.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Gray),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressBar(
                modifier = Modifier.size(120.dp),
                progress = 30f,
                progressMax = 100f,
                progressBarColor = Color.Blue,
                progressBarWidth = 20.dp,
                backgroundProgressBarColor = Color.Gray,
                backgroundProgressBarWidth = 10.dp,
                roundBorder = true,
                startAngle = 90f
            )
        }
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(DarkBackground),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                LazyRow(
                    contentPadding = PaddingValues(end = 8.dp),
                    content = {
                        items(localLocations) { location ->
                            CityItem(
                                modifier = Modifier.clickable {
                                    viewModel.saveToSharedPrefs(location.locationName)

                                    Toast
                                        .makeText(
                                            context, "${location.locationName} set as Default",
                                            Toast.LENGTH_LONG
                                        )
                                        .show()
                                },
                                backgroundColor = if (location.locationName == viewModel.currentLocation.value){
                                    Gray
                                }else DarkBackground,
                                location = location.locationName,
                                onLongClick = {
                                    //TODO()
                                }
                            )

                        }
                    }
                )


                Card(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .height(30.dp)
                        .clickable {
                            locationDialog.value = true
                        }
                        .background(Blue)
                        .width(30.dp)
                ) {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "Add City",
                        modifier = Modifier.size(ButtonDefaults.IconSize)
                    )
                }
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "${state.data?.location?.name}, ${state.data?.location?.country} ",
                    color = Color.White,
                    fontSize = 20.sp
                )


            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.padding(start = 16.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "${state.data?.current?.tempC}${0x00B0.toChar()}",
                        style = MaterialTheme.typography.h2.merge(),
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(
                                start = 18.dp,
                                end = 18.dp,
                            )
                    )
                    Text(
                        text = "${state.data?.current?.condition?.text}",
                        style = MaterialTheme.typography.body1.merge(),
                        color = Color.White,
                        textAlign = TextAlign.Start,
                        modifier = Modifier
                            .padding(
                                start = 18.dp,
                                bottom = 8.dp
                            )
                    )
                }

                Column(
                    modifier = Modifier
                        .aspectRatio(2f)
                        .padding(end = 16.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    AsyncImage(
                        model = ImageRequest.Builder(context)
                            .data("https:${state.data?.current?.condition?.icon}")
                            .crossfade(true)
                            .build(),
                        contentDescription = "${state.data?.current?.condition?.text}",
                        modifier = Modifier
                            .size(100.dp)
                    )
                }


            }

            Column(
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(10.dp)
                    )
                    .background(DarkBackground)
            ) {


                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DetailsItem(
                        text1 = "Feels Like",
                        textValue = "${state.data?.current?.feelslikeC}${0x00B0.toChar()}"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailsItem(
                        text1 = "Wind Speed",
                        textValue = "${state.data?.current?.windKph} kp/h"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailsItem(
                        text1 = "Pressure",
                        textValue = "${state.data?.current?.pressureMb} Mb"
                    )

                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    DetailsItem(
                        text1 = "Humidity",
                        textValue = "${state.data?.current?.humidity}%"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    //val windDir = "${state.data?.current?.windDir}"
                    DetailsItem(
                        text1 = "Wind direction",
                        textValue = "${state.data?.current?.windDir}"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    DetailsItem(
                        text1 = "Uv Index",
                        textValue = "${state.data?.current?.uv}"
                    )

                }
            }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 8.dp),
                content = {
                    val hourForecast: List<Forecastday> =
                        state.data?.forecast?.forecastday ?: emptyList()
                    items(hourForecast) {
                        it.hour.forEach { hour ->
                            HourItem(
                                icon = "https:${hour.condition?.icon}",
                                degrees = hour.tempC?.toFloat() ?: 0F,
                                time = hour.time!!,
                            )
                        }

                    }
                }
            )

            Column(
                modifier = Modifier
                    .size(height = 250.dp, width = 480.dp)
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LazyColumn(
                    contentPadding = PaddingValues(vertical = 2.dp),
                    content = {
                        val dailyForecast: List<Forecastday> =
                            state.data?.forecast?.forecastday ?: emptyList()
                        items(dailyForecast) { details ->
                            details.day.avgtempC?.toFloat()?.let { it1 ->
                                DailyItem(
                                    day = dayOfTheWeek(details.date!!),
                                    degrees = it1,
                                    icon = "https:${details.day.condition?.icon}"
                                )
                            }
                        }
                    })
            }

            if (locationDialog.value) {
                AlertDialog(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    onDismissRequest = { locationDialog.value = false },
                    title = {
                        Text(
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 16.sp,
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold
                            ),
                            text = "Add a Location",
                            modifier = Modifier.padding(8.dp),
                        )
                    },
                    text = {
                        TextField(
                            value = viewModel.locationDialogValue.value,
                            onValueChange = {
                                viewModel.setLocationDialogValue(it)
                            },
                            textStyle = TextStyle(color = Color.White),
                            placeholder = { Text(text = "Nairobi", color = Color.LightGray) },
                        )
                    },
                    confirmButton = {
                        Button(
                            colors = ButtonDefaults.buttonColors(Blue),
                            onClick = {
                                viewModel.addLocation()
                                locationDialog.value = false
                                Toast.makeText(context, "Locations added", Toast.LENGTH_LONG).show()
                            }
                        ) {
                            Text(text = "Add", color = Color.White)
                        }
                    },
                    backgroundColor = Gray,
                    contentColor = Color.White,
                    shape = RoundedCornerShape(12.dp)
                )


            }
        }
    }




