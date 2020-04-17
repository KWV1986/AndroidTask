package com.learning.infosysassignment.networkApi

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.learning.infosysassignment.model.NewsList
import com.learning.infosysassignment.util.NetworkUtility
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val retrofit = Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory()).baseUrl(NetworkUtility.baserUrl).build()

interface NewsApiService {

    @GET(NetworkUtility.endpoint)
    fun getNewsFromUrl(): Deferred<NewsList>

}

object NewsApi {
    val newsApiServive: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)

    }
}
