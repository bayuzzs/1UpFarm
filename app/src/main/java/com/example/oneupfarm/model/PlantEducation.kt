package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class PlantEducation(
    val chips: String,
    val title: String,
    val subTitle: String,
    val description: String,
    @DrawableRes val image: Int
)
