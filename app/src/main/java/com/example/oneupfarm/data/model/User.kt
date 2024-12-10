package com.example.oneupfarm.data.model

enum class Gender {
    F,
    M
}

data class User(
    val userId: Int?,
    val name: String,
    val email: String,
    val password: String?,
    val gender: Gender
)

