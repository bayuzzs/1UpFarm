package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class Notification(
    val id: Int,
    @DrawableRes val plantImage: Int,
    val content: String,
    val plantName: String,

)

enum class NotificationTimeStamp {
    TODAY, YESTERDAY, LAST_WEEK
}

enum class NotificationContent {

}