package com.patricio.dutra.desafiojeitto.data.datasource

import com.patricio.dutra.desafiojeitto.data.Retrofit
import com.patricio.dutra.desafiojeitto.model.News
import com.patricio.dutra.desafiojeitto.utils.Constants
import com.patricio.dutra.desafiojeitto.utils.NewsResponse
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsDataSource {

    fun getNews(NewsResponse: NewsResponse<ArrayList<News>>){

        Retrofit().endpoint.getNews(Constants.VALUEMAX, Constants.VALUESTART)
                .enqueue(object : Callback<ArrayList<News>> {

            override fun onResponse(call: Call<ArrayList<News>>, response: Response<ArrayList<News>>) {
                if (response.isSuccessful && response.body() != null) {
                    NewsResponse.success(response.body()!!)
                    Constants.setCurrentValue()
                } else if (response.errorBody() != null) {
                    val json = JSONObject(response.errorBody()!!.string())
                    NewsResponse.erro(json.getString("message").toString())
                }
            }

            override fun onFailure(result: Call<ArrayList<News>>, t: Throwable) {
                NewsResponse.erro(t.message.toString())
            }
        })
    }
}