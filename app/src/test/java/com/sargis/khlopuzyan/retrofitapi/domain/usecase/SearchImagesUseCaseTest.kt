package com.sargis.khlopuzyan.retrofitapi.domain.usecase

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.PixabayRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class SearchImagesUseCaseTest {

    private val pixabayRepository: PixabayRepository = mock()

    @Test
    fun test_success() = runTest {
        `when`(pixabayRepository.searchImagesByQuery("chicken")).thenReturn(
            Result.Success(
                getPixabayDtoResponse()
            )
        )
        val useCase = SearchImagesUseCaseImpl(pixabayRepository)
        val result = useCase.searchImagesByQuery("chicken")

        Assert.assertEquals(result.data, getPixabayDtoResponse())
    }

    @Test
    fun test_fail() = runTest {
        `when`(pixabayRepository.searchImagesByQuery("chicken")).thenReturn(
            Result.Error(UiError.UnknownError)
        )
        val useCase = SearchImagesUseCaseImpl(pixabayRepository)
        val result = useCase.searchImagesByQuery("chicken")

        Assert.assertEquals(result.uiError, UiError.UnknownError)
    }

    fun getPixabayDtoResponse(): PixabayDto {
        return PixabayDto(
            total = 10,
            totalHits = 10,
            hits = mutableListOf<HitDto>().also { list ->
                (0..10).forEach { index ->
                    list.add(
                        HitDto(
                            collections = index,
                            comments = index,
                            downloads = index,
                            id = index,
                            imageHeight = index,
                            imageSize = index,
                            imageWidth = index,
                            isAiGenerated = false,
                            isGRated = false,
                            isLowQuality = false,
                            largeImageURL = "$index",
                            likes = index,
                            noAiTraining = false,
                            pageURL = "$index",
                            previewHeight = index,
                            previewURL = "$index",
                            previewWidth = index,
                            tags = "$index",
                            type = "$index",
                            user = "$index",
                            userId = index,
                            userImageURL = "$index",
                            userURL = "$index",
                            views = index,
                            webformatHeight = index,
                            webformatURL = "$index",
                            webformatWidth = index
                        )
                    )
                }
            }
        )
    }
}