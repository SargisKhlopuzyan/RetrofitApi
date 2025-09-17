package com.sargis.khlopuzyan.retrofitapi.domain.usecase

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result

interface ImageSearchUseCase {
    suspend fun searchImageByQuery(query: String): Result<PixabayDto>
}

class ImageSearchUseCaseImpl(
    private val imageSearchRepository: ImageSearchRepository,
) : ImageSearchUseCase {
    override suspend fun searchImageByQuery(query: String): Result<PixabayDto> {
        return imageSearchRepository.searchImageByQuery(query)
    }
}