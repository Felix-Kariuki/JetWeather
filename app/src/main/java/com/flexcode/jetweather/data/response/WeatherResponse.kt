package com.flexcode.jetweather.data.response

import com.flexcode.jetweather.data.models.Current
import com.flexcode.jetweather.data.models.Forecast
import com.flexcode.jetweather.data.models.Location
import com.google.gson.annotations.SerializedName

data class WeatherResponse (

    @SerializedName("location" )
    var location : Location,
    @SerializedName("current"  )
    var current  : Current?  = Current(),
    @SerializedName("forecast" )
    var forecast : Forecast? = Forecast()

)