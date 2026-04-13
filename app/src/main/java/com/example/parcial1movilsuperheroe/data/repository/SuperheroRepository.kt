package com.example.parcial1movilsuperheroe.data.repository

import com.example.parcial1movilsuperheroe.data.remote.SuperheroApi
import com.example.parcial1movilsuperheroe.model.Superhero
import com.example.parcial1movilsuperheroe.model.toDomain

class SuperheroRepository(private val api: SuperheroApi) {

    // Cache to prevent duplicate calls and enable fast Detail view
    private var heroesCache: List<Superhero>? = null

    suspend fun getSuperheroes(): List<Superhero> {
        if (heroesCache != null) return heroesCache!!
        
        val dtos = api.getAllSuperheroes()
        val mapped = dtos.map { it.toDomain() }
        heroesCache = mapped
        return mapped
    }

    suspend fun getSuperheroById(id: Int): Superhero {
        // Try caching first
        val cached = heroesCache?.find { it.id == id }
        if (cached != null) return cached

        // Network fallback
        val dto = api.getSuperheroById(id)
        return dto.toDomain()
    }
}
