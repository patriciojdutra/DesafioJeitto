package com.patricio.dutra.desafiojeitto.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.Constants
import com.patricio.dutra.desafiojeitto.utils.Retrofit
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel : ViewModel() {

    val listNews = MutableLiveData<ArrayList<News>>()
    var list = ArrayList<News>()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<String>()

    fun getNews() {

        loading.value = true

        val callback =  Retrofit().endpoint.getNews(Constants.VALUEMAX,Constants.VALUESTART)

        callback.enqueue(object : Callback<ArrayList<News>> {

            override fun onResponse(call: Call<ArrayList<News>>, response: Response<ArrayList<News>>) {
                response?.let {

                    loading.value = false

                    if(response.isSuccessful) {
                        list.addAll(response.body()!!)
                        listNews.value = list
                        Constants.setCurrentValue()
                    }else if(response.errorBody() != null) {

                        it.errorBody()?.let{ result ->
                            var json:JSONObject = JSONObject(result.string())
                            error.value = json.getString("message").toString()
                        }
                    }
                }
            }


            override fun onFailure(result: Call<ArrayList<News>>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }
        })
    }
}