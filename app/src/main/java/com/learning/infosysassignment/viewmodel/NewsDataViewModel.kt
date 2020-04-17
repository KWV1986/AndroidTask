package com.learning.infosysassignment.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.learning.infosysassignment.model.NewsList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsDataViewModel(application: Application) : AndroidViewModel(application) {

    val repo = NewsDataRepo(application)
    val job = Job()
    private val scope = CoroutineScope(job + Dispatchers.Main)


    var newsData = MutableLiveData<NewsList?>()

    fun getAllNews(): LiveData<NewsList?> {

        scope.launch {


            val list = repo.getAllNewsData()
            newsData.apply { postValue(list) }


        }
        return newsData


    }

    class NewsDataModelFactory(val application: Application) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(NewsDataViewModel::class.java)) {
                return NewsDataViewModel(application) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }


    }

}

