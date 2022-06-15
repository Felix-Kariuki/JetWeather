package com.flexcode.jetweather.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.flexcode.jetweather.data.local.mappers.toLocations
import com.flexcode.jetweather.data.local.mappers.toLocationsEntity
import com.flexcode.jetweather.domain.model.Locations
import com.flexcode.jetweather.domain.repository.LocationsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocationsRepositoryImpl(
    private val dao: LocationsDao
) : LocationsRepository{
    override suspend fun addLocation(location: Locations) {
        dao.addLocation(location.toLocationsEntity())
    }

    override suspend fun deleteLocation(locations: Locations) {
        dao.deleteLocation(locations.toLocationsEntity())
    }

    /*override fun getAllLocations(): Flow<List<Locations>> {
        return dao.getAllLocations().map { locations ->
            locations.map { it.toLocations() }
        }
    }*/

    override fun getAllLocations(): LiveData<List<Locations>> {
        return Transformations.map(dao.getAllLocations()){ entity->
            entity.map {
                it.toLocations()
            }
        }
    }
}