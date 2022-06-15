package com.flexcode.jetweather.data.models

import com.google.gson.annotations.SerializedName

data class Alert(

    @SerializedName("headline")
    var headline: String? = null,
    @SerializedName("msgtype")
    var msgtype: String? = null,
    @SerializedName("severity")
    var severity: String? = null,
    @SerializedName("urgency")
    var urgency: String? = null,
    @SerializedName("areas")
    var areas: String? = null,
    @SerializedName("category")
    var category: String? = null,
    @SerializedName("certainty")
    var certainty: String? = null,
    @SerializedName("event")
    var event: String? = null,
    @SerializedName("note")
    var note: String? = null,
    @SerializedName("effective")
    var effective: String? = null,
    @SerializedName("expires")
    var expires: String? = null,
    @SerializedName("desc")
    var desc: String? = null,
    @SerializedName("instruction")
    var instruction: String? = null

)
