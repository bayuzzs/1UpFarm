package com.example.oneupfarm.model

import androidx.annotation.DrawableRes

data class ToDo(
    val id: Int,
    val plantName: String,
    @DrawableRes val plantImage: Int,
    val taskList: List<Task>
) {
    val completed: Boolean
        get() = taskList.all { it.checked }
}