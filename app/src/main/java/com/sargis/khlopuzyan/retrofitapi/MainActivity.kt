package com.sargis.khlopuzyan.retrofitapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sargis.khlopuzyan.retrofitapi.navigation.Navigation
import com.sargis.khlopuzyan.retrofitapi.ui.theme.RetrofitApiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitApiTheme {
                Navigation()
            }
        }
    }
}