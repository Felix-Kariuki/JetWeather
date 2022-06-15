package com.flexcode.jetweather.data.models

import com.google.gson.annotations.SerializedName

data class Forecastday(
    @SerializedName("date")
    var date: String? = null,
    @SerializedName("date_epoch")
    var dateEpoch: Int? = null,
    @SerializedName("day")
    var day: Day,
    @SerializedName("hour")
    var hour: ArrayList<Hour> = arrayListOf()
)