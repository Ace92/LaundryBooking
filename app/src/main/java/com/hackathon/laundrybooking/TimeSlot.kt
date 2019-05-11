package com.example.tvattroom

import java.util.*

data class TimeSlot(
        val id: String,
        val startTime: Date,
        val endTime: Date,
        val bookStatus: BookStatus,
        val isMy: Boolean
)