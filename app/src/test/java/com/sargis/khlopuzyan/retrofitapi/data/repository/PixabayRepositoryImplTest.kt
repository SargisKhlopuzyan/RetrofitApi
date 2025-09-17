package com.sargis.khlopuzyan.retrofitapi.data.repository

import com.sargis.khlopuzyan.retrofitapi.data.constant.PIXABAY_API_KEY
import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApiService
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class PixabayRepositoryImplTest {

    private val pixabayApiService: PixabayApiService = mock()

    @Test
    fun test_success() = runTest {
        `when`(
            pixabayApiService.getImagesByQuery(
                query = "chicken",
                accessKey = PIXABAY_API_KEY
            )
        ).thenReturn(getPixabayDtoResponse())

        val repo = ImageSearchRepositoryImpl(pixabayApiService)
        val response = repo.searchImageByQuery("chicken")

        assertEquals(getPixabayDtoResponse().hits, response.data?.hits)
    }

    @Test
    fun test_nullMealFromBackend() = runTest {
        `when`(
            pixabayApiService.getImagesByQuery(
                query = "chicken",
                accessKey = PIXABAY_API_KEY
            )
        ).thenThrow(RuntimeException(UiError.UnknownError.name))

        val repo = ImageSearchRepositoryImpl(pixabayApiService)
        val response: Result<PixabayDto> = repo.searchImageByQuery("chicken")
        val message = UiError.UnknownError.name

        assertEquals(response.uiError?.name, message)
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