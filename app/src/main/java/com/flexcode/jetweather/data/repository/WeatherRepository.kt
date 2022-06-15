package com.flexcode.jetweather.data.repository

import android.content.SharedPreferences
import com.flexcode.jetweather.data.response.WeatherResponse
import com.flexcode.jetweather.data.remote.ApiService
import com.flexcode.jetweather.utils.Constants.LOCATION_QUERY
import com.flexcode.jetweather.utils.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.HttpException
import java.io.IOException

class WeatherRepository(
    private val api: ApiService,
    private val sharedPreferences: SharedPreferences

) {
    val currentLocationQuery = MutableStateFlow(
        sharedPreferences.getString(LOCATION_QUERY,"Nairobi"))

    suspend fun getWeatherData(location : String) : Resource<WeatherResponse>{

        return try {
            Resource.Success(
                data = api.getWeather(location)
            )
        }catch (e: IOException){
            Resource.Error(message = "Error! ${e.message}")
        }catch (e:HttpException){
            Resource.Error(
                message = "Error!${e.localizedMessage}"
            )
        }
    }


    fun saveToSharedPrefs(locationName: String) {
        sharedPreferences.edit().putString(LOCATION_QUERY, locationName).apply()
        currentLocationQuery.value = locationName
    }
}