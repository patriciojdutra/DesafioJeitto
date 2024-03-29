package com.patricio.dutra.desafiojeitto.data

import com.patricio.dutra.desafiojeitto.model.News
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Endpoint {

    @GET("articles")
    fun getNews(@Query("_limit") limit: Int?, @Query("_start") start: Int?) : Call<ArrayList<News>>

}