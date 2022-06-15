package com.flexcode.jetweather.data.local.mappers

import com.flexcode.jetweather.data.local.LocationsEntity
import com.flexcode.jetweather.domain.model.Locations

fun LocationsEntity.toLocations(): Locations {
    return Locations(
        locationName = locationName,
        id = id
    )
}

fun Locations.toLocationsEntity(): LocationsEntity {
    return LocationsEntity(
        locationName = locationName,
        id = id
    )
}