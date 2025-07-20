package com.sargis.khlopuzyan.retrofitapi.domain.repository

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result

interface PixabayRepository {
    suspend fun searchImagesByQuery(query: String): Result<PixabayDto>
}