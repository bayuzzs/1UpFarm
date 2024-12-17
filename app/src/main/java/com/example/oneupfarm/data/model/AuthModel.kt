package com.example.oneupfarm.data.model

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val gender: Gender,
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class RegisterResponse(
    val status: Int,
    val name: String,
    val email: String,
    val gender: Gender,
)

data class LoginResponse(
    val status: Int,
    val message: String,
    val token: String
)

data class CheckUserRequest(
    val token: String
)

data class CheckUserResponse(
    val status: Int,
    val message: String,
    val data: CheckUser
)

data class CheckUser(
    val id: String,
    val name: String,
    val email: String,
)