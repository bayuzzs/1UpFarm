package com.example.oneupfarm.data.api

import com.example.oneupfarm.data.model.Plant
import com.example.oneupfarm.data.model.PlantResponse
import com.example.oneupfarm.data.model.SingleUserPlantResponse
import com.example.oneupfarm.data.model.UserPlant
import com.example.oneupfarm.data.model.UserPlantResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface PlantApi {
    @GET("plant")
    suspend fun getPlants(): Response<PlantResponse>

    @POST("userPlant")
    suspend fun addUserPlant(@Body userPlant: UserPlant): Response<ResponseBody>

    @GET("userPlant")
    suspend fun getUserPlants(): Response<UserPlantResponse>

    @GET("userPlant/{userPlantId}")
    suspend fun getUserPlantById(@Path("userPlantId") userPlantId: Int): Response<SingleUserPlantResponse>
}