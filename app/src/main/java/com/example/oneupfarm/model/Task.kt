package com.example.oneupfarm.model

data class Task(
    val id: Int,
    val desc: String,
    val expValue: Int?,
    val goldValue: Int?,
    val checked: Boolean = false
)