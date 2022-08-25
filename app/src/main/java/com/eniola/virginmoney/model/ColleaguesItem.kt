package com.eniola.virginmoney.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ColleaguesItem (
    @Json(name = "avatar")
    val profile: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "email")
    val email: String,
    @Json(name = "favouriteColor")
    val favouriteColor: String,
    @Json(name = "firstName")
    val firstName: String,
    @Json(name = "id")
    val id: String,
    @Json(name = "jobtitle")
    val jobtitle: String,
    @Json(name = "lastName")
    val lastName: String
    )