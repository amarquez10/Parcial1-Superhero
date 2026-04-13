package com.example.parcial1movilsuperheroe.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val BASE_URL = "https://akabab.github.io/superhero-api/api/"

    val api: SuperheroApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SuperheroApi::class.java)
    }
}
