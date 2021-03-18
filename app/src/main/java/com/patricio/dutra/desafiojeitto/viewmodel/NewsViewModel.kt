package com.patricio.dutra.desafiojeitto.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patricio.dutra.desafiojeitto.data.Retrofit
import com.patricio.dutra.desafiojeitto.data.repository.NewsRepository
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.Constants
import com.patricio.dutra.desafiojeitto.utils.NewsResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(repository: NewsRepository) : ViewModel() {

    val listNews = MutableLiveData<ArrayList<News>>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()
    val repository = repository

    fun getNews() {
        loading.value = true

        repository.getNews(object : NewsResponse<ArrayList<News>> {
            override fun success(result: ArrayList<News>) {
                loading.value = false
                listNews.value = result
            }
            override fun erro(result: String) {
                loading.value = false
                error.value = result
            }
        })
    }
}