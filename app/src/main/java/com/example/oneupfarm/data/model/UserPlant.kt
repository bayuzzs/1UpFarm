package com.example.oneupfarm.data.model

data class UserPlantResponse(
    val data: List<UserPlant>
)

data class SingleUserPlantResponse(
    val data: UserPlant
)

data class UserPlant(
    val userPlantId: Int? = null,
    val userId: Int? = null,
    val plantId: Int,
    val PlantDate: String,
    val harvestDate: String,
    val Method_plant: String,
    val Location_Plant: String,
    val IsComplate: String,
    val plantName: String? = null,
    val difficulty: String? = null,
    val urlPicture: String? = null
)
