package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class ChoosePlant(
    val name: String,
    @DrawableRes val icon: Int,
    val difficulty: String
)
