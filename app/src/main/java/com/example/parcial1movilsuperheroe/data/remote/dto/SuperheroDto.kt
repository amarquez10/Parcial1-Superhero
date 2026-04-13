package com.example.parcial1movilsuperheroe.data.remote.dto

data class SuperheroDto(
    val id: Int,
    val name: String,
    val slug: String?,
    val powerstats: PowerstatsDto?,
    val appearance: AppearanceDto?,
    val biography: BiographyDto?,
    val work: WorkDto?,
    val connections: ConnectionsDto?,
    val images: ImagesDto
)

data class PowerstatsDto(
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val durability: Int,
    val power: Int,
    val combat: Int
)

data class AppearanceDto(
    val gender: String?,
    val race: String?,
    val height: List<String>?,
    val weight: List<String>?,
    val eyeColor: String?,
    val hairColor: String?
)

data class BiographyDto(
    val fullName: String?,
    val alterEgos: String?,
    val aliases: List<String>?,
    val placeOfBirth: String?,
    val firstAppearance: String?,
    val publisher: String?,
    val alignment: String?
)

data class WorkDto(
    val occupation: String?,
    val base: String?
)

data class ConnectionsDto(
    val groupAffiliation: String?,
    val relatives: String?
)

data class ImagesDto(
    val xs: String?,
    val sm: String,
    val md: String?,
    val lg: String
)
