package com.learning.infosysassignment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.learning.infosysassignment.adapters.NewsAdapter
import com.learning.infosysassignment.model.NewsList
import com.learning.infosysassignment.networkApi.NewsApi
import com.learning.infosysassignment.util.ConnectionUtility
import com.learning.infosysassignment.viewmodel.NewsDataRepo
import com.learning.infosysassignment.viewmodel.NewsDataViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    lateinit var viewModel: NewsDataViewModel
    var news: NewsList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i("Activity","InOncreate()")
        initviews()

    }

    private fun initviews() {

        // getToolbar
        val actionBar = supportActionBar
        val application = this.application
        val newsDataRepo = NewsDataRepo(application)
        val factory = NewsDataViewModel.NewsDataModelFactory(application)
        viewModel = ViewModelProviders.of(this, factory).get(NewsDataViewModel::class.java)


        // check internet is available or not
        if (ConnectionUtility.isOnline(this)) {
            progress_bar.visibility= View.VISIBLE
            viewModel.getAllNews().observe(this, Observer { it ->
                val news = it
                actionBar?.title = news?.title
                recycle_view.adapter = NewsAdapter(application, news)
                progress_bar.visibility=View.INVISIBLE

            })


        } else {
            Toast.makeText(this, "Please start our connection", Toast.LENGTH_LONG).show()
        }


        swipeContainer.setOnRefreshListener(object : SwipeRefreshLayout.OnRefreshListener {
            override fun onRefresh() {
                if(ConnectionUtility.isOnline(this@MainActivity)){
                    swipeContainer.isRefreshing = true
                    updateNewsData()
                }
                else {
                    Toast.makeText(this@MainActivity, "Please start our connection", Toast.LENGTH_LONG).show()
                }





            }

        })





    }

    private fun updateNewsData() {
        val job = Job()
        CoroutineScope(job + Dispatchers.Main).launch {
            val res = NewsApi.newsApiServive.getNewsFromUrl().await()
            recycle_view.adapter = NewsAdapter(application, res)
        }
        swipeContainer.isRefreshing = false


    }
}


