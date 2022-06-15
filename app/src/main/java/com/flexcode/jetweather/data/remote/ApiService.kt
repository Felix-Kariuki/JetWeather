package com.flexcode.jetweather.data.remote

import com.flexcode.jetweather.data.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    //http://api.weatherapi.com/v1/forecast.json?key=544663132115456&q=London&days=1&aqi=no&alerts=no

    @GET("forecast.json")
    suspend fun getWeather(
        @Query("q") query : String,
        @Query("key") key : String = "",
        @Query("days") days : Int = 3,
        @Query("aqi") aqi : String = "no",
        @Query("alerts") alerts : String = "no",
    ) : WeatherResponse
}