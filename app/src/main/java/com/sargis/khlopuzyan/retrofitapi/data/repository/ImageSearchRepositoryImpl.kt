package com.sargis.khlopuzyan.retrofitapi.data.repository

import com.sargis.khlopuzyan.retrofitapi.data.constant.PIXABAY_API_KEY
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApiService
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError

class ImageSearchRepositoryImpl(
    private val pixabayApiService: PixabayApiService,
) : ImageSearchRepository {
    override suspend fun searchImageByQuery(query: String): Result<PixabayDto> {
        return try {
            val pixabayDto = pixabayApiService.getImagesByQuery(query, PIXABAY_API_KEY)
            Result.Success(pixabayDto)
        } catch (e: Exception) {
            println("SearchByQueryRepositoryImpl -> searchByQuery -> e: $e")
            Result.Error(UiError.UnknownError)
        }
    }
}