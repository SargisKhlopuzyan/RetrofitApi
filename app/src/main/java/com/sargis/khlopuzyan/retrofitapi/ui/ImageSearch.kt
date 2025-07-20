package com.sargis.khlopuzyan.retrofitapi.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.sargis.khlopuzyan.retrofitapi.ui.theme.RetrofitApiTheme

@Composable
fun ImageSearch() {
    val viewModel: ImageSearchViewModel = viewModel()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp),
        ) {
            Button(onClick = {
                viewModel.searchImageByQuery("Banana")
            }) {
                Text(text = "Search")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ImageSearchPreview() {
    RetrofitApiTheme {
        ImageSearch()
    }
}