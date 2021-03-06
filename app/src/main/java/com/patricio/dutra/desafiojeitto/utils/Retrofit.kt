package com.patricio.dutra.desafiojeitto.utils

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Retrofit {

    fun getRetrofitInstance() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(Constants.URLSERVER)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val endpoint: Endpoint = getRetrofitInstance().create(Endpoint::class.java)
}