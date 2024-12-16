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
    val gender: Gender,
    val avatar: Avatar? = null
)

data class Avatar(
    val avatarId: Int,
    val exp: Int,
    val level: Int,
    val gold: Int,
    val health: Int,
    val maxHealth: Int
)