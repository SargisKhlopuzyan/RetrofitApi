package com.sargis.khlopuzyan.retrofitapi.data.constant

import com.sargis.khlopuzyan.retrofitapi.data.remote.PixabayApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object PixabayApiRetrofitBuilder {
    private const val PIXABAY_BASE_URL = "https://pixabay.com/"

    fun build(): PixabayApi {
        val okHttpClient = OkHttpClient.Builder().apply {
            connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
        }.build()

        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
        return Retrofit.Builder()
            .baseUrl(PIXABAY_BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
            .create()
    }
}