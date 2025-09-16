package com.sargis.khlopuzyan.retrofitapi.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.sargis.khlopuzyan.retrofitapi.data.remote.dto.HitDto
import com.sargis.khlopuzyan.retrofitapi.ui.theme.RetrofitApiTheme

@Composable
fun ImageSearch() {
    val viewModel: ImageSearchViewModel = viewModel()

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

            Button(onClick = {
                viewModel.searchImageByQuery(inputText)
            }) {
                Text(text = "Search")
            }

            Spacer(modifier = Modifier.height(4.dp))

            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(0.dp)
                    .weight(1.0f),
                columns = GridCells.Fixed(2),
            ) {
                viewModel.state.value.data.let {
                    items(it) { hit ->
                        MainContentItem(hit)
                    }
                }
            }
        }
    }
}

@Composable
fun MainContentItem(hit: HitDto) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
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
fun ImageSearchPreview() {
    RetrofitApiTheme {
        ImageSearch()
    }
}