package com.eniola.virginmoney.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class OfficeItem (
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "isOccupied")
    val notVacant: Boolean,
    @Json(name = "maxOccupancy")
    val maxOccupancy: Int
    )