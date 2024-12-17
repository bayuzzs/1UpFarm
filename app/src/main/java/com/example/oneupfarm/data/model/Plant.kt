package com.example.oneupfarm.data.model

data class PlantResponse(
    val results: List<Plant>
)

data class Plant(
    val plantId: Int,
    val urlPicture: String,
    val harvestDay: Int,
    val plantName: String,
    val difficulty: String
)