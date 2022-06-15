package com.flexcode.jetweather.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "location_table")
data class LocationsEntity(
    val locationName: String,
    @PrimaryKey(autoGenerate = true) val id: Int? = null
)