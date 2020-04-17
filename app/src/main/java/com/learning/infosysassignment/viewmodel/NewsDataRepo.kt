package com.learning.infosysassignment.viewmodel

import android.app.Application
import com.learning.infosysassignment.model.NewsList
import com.learning.infosysassignment.networkApi.NewsApi

class NewsDataRepo(application: Application) {
    // make apicall  here
    suspend fun getAllNewsData(): NewsList {
        val res = NewsApi.newsApiServive.getNewsFromUrl().await()
        return res
    }
}