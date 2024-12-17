package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class LeaderboardUser(
    val id: Int,
    @DrawableRes val avatarImage: Int,
    val name: String,
    val level: Int,
    val goldValue: Int
)
