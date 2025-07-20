package com.sargis.khlopuzyan.retrofitapi.data.remote.dto

import java.io.Serializable

data class PixabayDto(
    val total: Int,
    val totalHits: Int,
    val hits: List<HitDto>,
) : Serializable