package com.sargis.khlopuzyan.retrofitapi.domain.repository

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result

interface ImageSearchRepository {
    suspend fun searchImageByQuery(query: String): Result<PixabayDto>
}