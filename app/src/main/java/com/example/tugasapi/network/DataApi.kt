package com.example.tugasapi.network

import com.squareup.moshi.Json

data class DataApi (
    @Json(name="name")
    val name: String,
    @Json(name="species")
    val species: String,
    @Json(name="image")
    val image: String,
)

data class DataApiResponse(
    @Json(name="results")
    val result : List<DataApi>
)