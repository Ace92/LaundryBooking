package com.example.tvattroom

sealed class BookStatus
object Booked: BookStatus()
object Free: BookStatus()
object Maintain: BookStatus()