package com.nazlasalsabila.datafetcherapp

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse(
    val message: String,
    val code: String,
    val data: List<String>
)