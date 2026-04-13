package com.example.parcial1movilsuperheroe.data.remote

import com.example.parcial1movilsuperheroe.data.remote.dto.SuperheroDto
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperheroApi {

    @GET("all.json")
    suspend fun getAllSuperheroes(): List<SuperheroDto>

    @GET("id/{heroId}.json")
    suspend fun getSuperheroById(@Path("heroId") id: Int): SuperheroDto
}
