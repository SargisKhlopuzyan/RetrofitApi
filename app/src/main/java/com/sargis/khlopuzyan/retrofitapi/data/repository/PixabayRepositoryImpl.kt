package com.sargis.khlopuzyan.retrofitapi.data.repository

import com.sargis.khlopuzyan.retrofitapi.data.constant.PIXABAY_API_KEY
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApi
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.PixabayRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PixabayRepositoryImpl(
    private val pixabayApi: PixabayApi,
) : PixabayRepository {
    override suspend fun searchImagesByQuery(query: String): Result<PixabayDto> {
        return try {
            withContext(Dispatchers.IO) {
                val pixabayDto = pixabayApi.getImagesByQuery(query, PIXABAY_API_KEY)
                Result.Success(pixabayDto)
            }
        } catch (e: Exception) {
            println("Exception: $e")
            Result.Error(UiError.UnknownError)
        }
    }
}