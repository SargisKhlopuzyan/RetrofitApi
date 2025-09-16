package com.sargis.khlopuzyan.retrofitapi.data.repository

import com.sargis.khlopuzyan.retrofitapi.data.constant.PIXABAY_API_KEY
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApiService
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.PixabayRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError

class PixabayRepositoryImpl(
    private val pixabayApiService: PixabayApiService,
) : PixabayRepository {
    override suspend fun searchImagesByQuery(query: String): Result<PixabayDto> {
        return try {
            val pixabayDto = pixabayApiService.getImagesByQuery(query, PIXABAY_API_KEY)
            Result.Success(pixabayDto)
        } catch (e: Exception) {
            println("RetrofitApi -> searchImagesByQuery -> e: $e")
            Result.Error(UiError.UnknownError)
        }
    }
}