package com.sargis.khlopuzyan.retrofitapi.ui.imageSearchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.PixabayDto
import com.sargis.khlopuzyan.retrofitapi.ui.theme.RetrofitApiTheme

@Composable
fun ImageSearchScreen(
    navController: NavHostController,
    uiState: State<UiState>,
    searchImage: (String) -> Unit,
    testClicked: () -> Unit,
    navigateToImageDetailScreen: (Int) -> Unit,
) {

    var inputText by remember {
        mutableStateOf("")
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = inputText,
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                onValueChange = {
                    inputText = it
                }
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row {
                Button(onClick = {
                    searchImage(inputText)
                }) {
                    Text(text = "Search")
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(onClick = {
                    testClicked()
                }) {
                    Text(text = "Test")
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1.0f),
                columns = GridCells.Fixed(2),
            ) {
                uiState.value.data.let {
                    items(it) { hit ->
                        MainContentItem(hit, onClick = navigateToImageDetailScreen)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentItem(hit: HitDto, onClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp),
        onClick = {
            onClick(hit.id)
        }
    ) {
        Image(
            painter = rememberImagePainter(data = hit.largeImageURL),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    RetrofitApiTheme {
        val uiState = remember {
            mutableStateOf<UiState>(UiState(data = getPixabayDtoResponse().hits))
        }
        ImageSearchScreen(rememberNavController(), uiState, {}, {}) {

        }
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