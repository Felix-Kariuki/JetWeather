package com.flexcode.jetweather.presentation.compossables.screens.home

import com.flexcode.jetweather.data.response.WeatherResponse
import com.flexcode.jetweather.domain.model.Locations

data class HomeState(
    val isLoading : Boolean = false,
    val data: WeatherResponse? = null
)