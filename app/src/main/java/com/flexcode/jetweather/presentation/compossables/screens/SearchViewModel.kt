package com.flexcode.jetweather.presentation.compossables.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexcode.jetweather.data.repository.WeatherRepository
import com.flexcode.jetweather.presentation.compossables.screens.home.HomeState
import com.flexcode.jetweather.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel(){
    private val _query = mutableStateOf("")
    val query: State<String> = _query

    fun setSearchQuery(location: String){
        _query.value = location
    }

    private val _state = mutableStateOf(HomeState())
    val state: State<HomeState> = _state


    fun search(location: String){
        _query.value = location

        viewModelScope.launch {
            _state.value = state.value.copy(
                isLoading = true
            )
            val result = repository.getWeatherData(_query.value)
            when(result){
                is Resource.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        data = result.data
                    )
                }
                is Resource.Error-> {
                    _state.value = state.value.copy(
                        isLoading = true,
                    )
                }
                else -> {

                }
            }
        }
    }

}