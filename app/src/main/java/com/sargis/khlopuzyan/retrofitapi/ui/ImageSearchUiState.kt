package com.sargis.khlopuzyan.retrofitapi.ui

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto

data class ImageSearchUiState(
    val isLoading: Boolean = false,
    val data: List<HitDto> = emptyList(),
    val error: String = "",
)