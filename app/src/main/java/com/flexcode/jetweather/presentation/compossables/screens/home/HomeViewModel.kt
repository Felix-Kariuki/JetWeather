package com.flexcode.jetweather.presentation.compossables.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexcode.jetweather.data.repository.WeatherRepository
import com.flexcode.jetweather.domain.model.Locations
import com.flexcode.jetweather.domain.repository.LocationsRepository
import com.flexcode.jetweather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository,
    private val repository: LocationsRepository
) : ViewModel() {

    private val _locationDialogValue = mutableStateOf("")
    val locationDialogValue: State<String> = _locationDialogValue

    private val _currentLocation = mutableStateOf("")
    val currentLocation: State<String> = _currentLocation

    val allLocations = repository.getAllLocations()

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state

    private fun notifyCurrentLocation(): StateFlow<String?> = weatherRepo.currentLocationQuery


    init {
        viewModelScope.launch {
            weatherRepo.currentLocationQuery.collect {
                _currentLocation.value = it.toString()
            }
        }
        viewModelScope.launch {
            notifyCurrentLocation().collect { location ->
                location?.let { getWeatherDetails(it) }
            }
        }
    }

    private fun getWeatherDetails(location: String) {
        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = weatherRepo.getWeatherData(location)
            when(result){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        data = result.data,
                        isLoading = false
                    )
                }
                is Resource.Error -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                    )
                }

            }

        }
    }

    fun saveToSharedPrefs(locationName: String) {
        weatherRepo.saveToSharedPrefs(locationName)
    }

    fun setLocationDialogValue(text: String) {
        _locationDialogValue.value = text
    }

    suspend fun deleteLocation(location: Locations) {
        repository.deleteLocation(location)
    }

    fun addLocation() {
        viewModelScope.launch {
            if (locationDialogValue.value.isNotBlank()) {
                repository.addLocation(
                    Locations(
                        locationDialogValue.value
                    )
                )
            } else return@launch
        }
    }
}