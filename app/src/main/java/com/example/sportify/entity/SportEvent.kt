package com.example.sportify.entity

import java.util.Date

data class SportEvent(
    val eventName: String = "",
    val level: String = "",
    val location: String = "",
    val date: String = "",
    val time: String = "",
    val sportCategory: String = "",
    val maxParticipants: String = "",
    val duration: String = "",
    val status: String = SportEventStatus.OPEN.name,
)

enum class SportEventStatus {
    OPEN, CLOSED
}