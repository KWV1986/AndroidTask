package com.learning.infosysassignment.model


import com.squareup.moshi.Json

data class Row(
    @Json(name = "description")
    val description: String?,
    @Json(name = "imageHref")
    val imageHref: String?,
    @Json(name = "title")
    val title: String?
)