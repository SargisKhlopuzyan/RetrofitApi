package com.sargis.khlopuzyan.retrofitapi.domain.usecase

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.PixabayRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result

interface SearchImagesUseCase {
    suspend fun searchImagesByQuery(query: String): Result<PixabayDto>
}

class SearchImagesUseCaseImpl(
    private val pixabayRepository: PixabayRepository,
) : SearchImagesUseCase {
    override suspend fun searchImagesByQuery(query: String): Result<PixabayDto> {
        return pixabayRepository.searchImagesByQuery(query)
    }
}