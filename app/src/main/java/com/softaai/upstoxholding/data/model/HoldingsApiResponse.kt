package com.softaai.upstoxholding.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HoldingsApiResponse(
    @Json(name = "client_id")
    val clientId: String,
    @Json(name = "data")
    val `data`: List<Data>,
    @Json(name = "error")
    val error: Any,
    @Json(name = "response_type")
    val responseType: String,
    @Json(name = "timestamp")
    val timestamp: Long
)