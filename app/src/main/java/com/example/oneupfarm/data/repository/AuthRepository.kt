package com.example.oneupfarm.data.repository

import com.example.oneupfarm.data.api.AuthApi
import com.example.oneupfarm.data.local.TokenManager
import com.example.oneupfarm.data.model.Gender
import com.example.oneupfarm.data.model.LoginRequest
import com.example.oneupfarm.data.model.RegisterRequest
import com.example.oneupfarm.data.model.User
import okhttp3.ResponseBody
import retrofit2.Response

class AuthRepository(
    private val api: AuthApi,
    private val tokenManager: TokenManager
) {
    suspend fun login(email: String, password: String) {
        val response = api.loginUser(LoginRequest(email, password))
        tokenManager.saveToken(response.token)
    }

    suspend fun register(name: String, email: String, password: String, gender: Gender) {
        val response = api.registerUser(RegisterRequest(name, email, password, gender))
        // Simpan token jika register langsung login otomatis
        // tokenManager.saveToken(response.token)
    }

    suspend fun getUserInfo(): Response<User>{
        val response = api.getUserInfo()
        return response
    }


    fun getToken(): String? {
        return tokenManager.getToken()
    }

    fun clearToken() {
        tokenManager.clearToken()
    }
}
