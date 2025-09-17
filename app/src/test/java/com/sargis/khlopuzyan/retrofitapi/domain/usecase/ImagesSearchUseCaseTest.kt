package com.sargis.khlopuzyan.retrofitapi.domain.usecase

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.repository.ImageSearchRepository
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class ImagesSearchUseCaseTest {

    private val imageSearchRepository: ImageSearchRepository = mock()

    @Test
    fun test_success() = runTest {
        `when`(imageSearchRepository.searchImageByQuery("chicken")).thenReturn(
            Result.Success(
                getPixabayDtoResponse()
            )
        )
        val useCase = ImageSearchUseCaseImpl(imageSearchRepository)
        val result = useCase.searchImageByQuery("chicken")

        Assert.assertEquals(result.data, getPixabayDtoResponse())
    }

    @Test
    fun test_fail() = runTest {
        `when`(imageSearchRepository.searchImageByQuery("chicken")).thenReturn(
            Result.Error(UiError.UnknownError)
        )
        val useCase = ImageSearchUseCaseImpl(imageSearchRepository)
        val result = useCase.searchImageByQuery("chicken")

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