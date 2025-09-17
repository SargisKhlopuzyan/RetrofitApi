package com.sargis.khlopuzyan.retrofitapi.ui.searchImageScreen

import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.domain.usecase.ImageSearchUseCase
import com.sargis.khlopuzyan.retrofitapi.domain.util.Result
import com.sargis.khlopuzyan.retrofitapi.domain.util.UiError
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.NavigationEvent
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.ImageSearchViewModel
import com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen.UiEvent
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class SearchScreenViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun before() {

    }

    @After
    fun after() {

    }

    private val imageSearchUseCase: ImageSearchUseCase = Mockito.mock()

    @Test
    fun test_success() = runTest {
        // TODO - We don't need this as we have @get:Rule instead of this
//        Dispatchers.setMain(UnconfinedTestDispatcher())
        Mockito.`when`(imageSearchUseCase.searchImageByQuery("chicken")).thenReturn(
            Result.Success(getPixabayDtoResponse())
        )

        val viewModel = ImageSearchViewModel(imageSearchUseCase)

        viewModel.onEvent(UiEvent.OnSearchImage("chicken"))

        TestCase.assertEquals(viewModel.uiState.value.data, getPixabayDtoResponse().hits)
//        Dispatchers.resetMain()
    }

    @Test
    fun test_fail() = runTest {
//        Dispatchers.setMain(UnconfinedTestDispatcher())
        Mockito.`when`(imageSearchUseCase.searchImageByQuery("chicken")).thenReturn(
            Result.Error(UiError.NoInternetConnectionError)
        )

        val viewModel = ImageSearchViewModel(imageSearchUseCase)

        viewModel.onEvent(UiEvent.OnSearchImage("chicken"))

        TestCase.assertEquals(viewModel.uiState.value.error, UiError.NoInternetConnectionError.name)
//        Dispatchers.resetMain()
    }

    @Test
    fun test_navigate_detail_screen() = runTest {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        val viewModel = ImageSearchViewModel(imageSearchUseCase)
        viewModel.onNavigationEvent(NavigationEvent.NavigateDetailScreen(1))

        val list = mutableListOf<NavigationEvent>()

        backgroundScope.launch(UnconfinedTestDispatcher()) {
            viewModel.navigation.collectLatest {
                list.add(it)
            }
        }

        assert(list.first() is NavigationEvent.NavigateDetailScreen)
        Dispatchers.resetMain()
    }

    class MainDispatcherRule(
        val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(),
    ) : TestWatcher() {

        override fun starting(description: Description?) {
            Dispatchers.setMain(testDispatcher)
        }

        override fun finished(description: Description?) {
            Dispatchers.resetMain()
        }
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