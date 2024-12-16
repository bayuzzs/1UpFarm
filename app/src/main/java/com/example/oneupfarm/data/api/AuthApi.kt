package com.example.oneupfarm.data.api

import com.example.oneupfarm.data.model.CheckUserRequest
import com.example.oneupfarm.data.model.CheckUserResponse
import com.example.oneupfarm.data.model.LoginRequest
import com.example.oneupfarm.data.model.LoginResponse
import com.example.oneupfarm.data.model.RegisterRequest
import com.example.oneupfarm.data.model.RegisterResponse
import com.example.oneupfarm.data.model.User
import okhttp3.ResponseBody
import retrofit2.Response
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

    @GET("auth/me")
    suspend fun getUserInfo(): Response<User>

//    @POST("/auth/refresh")
//    suspend fun refreshAccessToken(@Header("Authorization") refreshToken: String): Response<RefreshTokenResponse>

}