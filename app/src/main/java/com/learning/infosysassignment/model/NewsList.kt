package com.learning.infosysassignment.model


import com.squareup.moshi.Json

data class NewsList(
    @Json(name = "rows")
    val rows: List<Row>,
    @Json(name = "title")
    val title: String
)