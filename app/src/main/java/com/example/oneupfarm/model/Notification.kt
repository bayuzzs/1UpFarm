package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class Notification(
    val id: Int,
    @DrawableRes val plantImage: Int,
    val content: String,
    val plantName: String,
    val timeStamp: String,
    val notifType: NotifType,
    val seen: Boolean
)

enum class NotifType {
    TODAY,
    NOT_TODAY
}