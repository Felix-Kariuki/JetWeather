package com.flexcode.jetweather.domain.repository

import androidx.lifecycle.LiveData
import com.flexcode.jetweather.domain.model.Locations
import kotlinx.coroutines.flow.Flow

interface LocationsRepository {


    suspend fun addLocation(location: Locations)

    suspend fun deleteLocation(locations: Locations)

    fun getAllLocations() : LiveData<List<Locations>>
}