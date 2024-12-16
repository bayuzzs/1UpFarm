package com.example.oneupfarm.data.api

import android.content.Context
import com.example.oneupfarm.data.api.RetrofitClient.STATIC_BASE_URL
import com.example.oneupfarm.data.local.TokenManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://dfd0-103-191-250-218.ngrok-free.app/api/v1/"
    private const val STATIC_BASE_URL = "https://dfd0-103-191-250-218.ngrok-free.app/"


    fun create(context: Context, onUnauthorized: () -> Unit): Retrofit {
        val tokenManager = TokenManager(context)
        val client = OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(
                tokenProvider = { tokenManager.getToken() },
                onUnauthorized = onUnauthorized
            ))
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }


    fun createStatic(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(STATIC_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}



