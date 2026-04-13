package com.example.parcial1movilsuperheroe.model

import com.example.parcial1movilsuperheroe.data.remote.dto.SuperheroDto

data class Superhero(
    val id: Int,
    val name: String,
    val fullName: String,
    val publisher: String,
    val imageUrlSm: String,
    val imageUrlLg: String,
    val placeOfBirth: String,
    val firstAppearance: String,
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int
)

fun SuperheroDto.toDomain() = Superhero(
    id = id,
    name = name,
    fullName = biography?.fullName ?: "Desconocido",
    publisher = biography?.publisher ?: "Desconocido",
    imageUrlSm = images.sm,
    imageUrlLg = images.lg,
    placeOfBirth = biography?.placeOfBirth ?: "-",
    firstAppearance = biography?.firstAppearance ?: "-",
    intelligence = powerstats?.intelligence ?: 0,
    strength = powerstats?.strength ?: 0,
    speed = powerstats?.speed ?: 0,
    durability = powerstats?.durability ?: 0,
    power = powerstats?.power ?: 0,
    combat = powerstats?.combat ?: 0
)
