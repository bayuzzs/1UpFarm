package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class Badge(
    val id: Int,
    @DrawableRes val badgeImage: Int,
    val badgeTitle: String
)