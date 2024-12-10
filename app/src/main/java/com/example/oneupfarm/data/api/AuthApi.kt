package com.example.oneupfarm.data.api

import com.example.oneupfarm.data.model.CheckUserRequest
import com.example.oneupfarm.data.model.CheckUserResponse
import com.example.oneupfarm.data.model.LoginRequest
import com.example.oneupfarm.data.model.LoginResponse
import com.example.oneupfarm.data.model.RegisterRequest
import com.example.oneupfarm.data.model.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/register")
    suspend fun registerUser(@Body request: RegisterRequest): RegisterResponse

    @POST("auth/login")
    suspend fun loginUser(@Body request: LoginRequest): LoginResponse

    @POST("auth/check")
    suspend fun checkUser(@Body request: CheckUserRequest): CheckUserResponse
}

interface Test {
    @GET("/")
    suspend fun getHome(): GetHomeResponse
}

data class GetHomeResponse(
    val status: String,
    val message: String,
    val results: String
)