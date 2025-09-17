package com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto

data class UiState(
    val isLoading: Boolean = false,
    val data: List<HitDto> = emptyList(),
    val error: String = "",
)