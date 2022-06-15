package com.flexcode.jetweather.data.models

import com.google.gson.annotations.SerializedName


data class Day(

    @SerializedName("maxtemp_c")
    var maxtempC: Double? = null,
    @SerializedName("maxtemp_f")
    var maxtempF: Double? = null,
    @SerializedName("mintemp_c")
    var mintempC: Double,
    @SerializedName("mintemp_f")
    var mintempF: Double? = null,
    @SerializedName("avgtemp_c")
    var avgtempC: Double? = null,
    @SerializedName("avgtemp_f")
    var avgtempF: Double? = null,
    @SerializedName("maxwind_mph")
    var maxwindMph: Double? = null,
    @SerializedName("maxwind_kph")
    var maxwindKph: Double? = null,
    @SerializedName("totalprecip_mm")
    var totalprecipMm: Double? = null,
    @SerializedName("totalprecip_in")
    var totalprecipIn: Double? = null,
    @SerializedName("avgvis_km")
    var avgvisKm: Double? = null,
    @SerializedName("avgvis_miles")
    var avgvisMiles: Double? = null,
    @SerializedName("avghumidity")
    var avghumidity: Double? = null,
    @SerializedName("daily_will_it_rain")
    var dailyWillItRain: Double? = null,
    @SerializedName("daily_chance_of_rain")
    var dailyChanceOfRain: Double? = null,
    @SerializedName("daily_will_it_snow")
    var dailyWillItSnow: Double? = null,
    @SerializedName("daily_chance_of_snow")
    var dailyChanceOfSnow: Double? = null,
    @SerializedName("condition")
    var condition: Condition? = Condition(),
    @SerializedName("uv")
    var uv: Double
)