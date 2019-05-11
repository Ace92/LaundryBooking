package com.hackathon.laundrybooking

import com.example.tvattroom.Booked
import com.example.tvattroom.Free
import com.example.tvattroom.Maintain
import com.example.tvattroom.TimeSlot
import java.util.*
import kotlin.random.Random

class DummyRepository {
    val gap: Int = 3
    private val list = mutableMapOf<Int, MonthSlots>()
    fun getAvaliableSlots(): Map<Int, MonthSlots> {
        return list
    }

    init {
        generateData()
    }

    private fun generateData() {
        val randomStatus = Random(3)
        val randomMaintainStatus = Random(2)
        val listOfDaySlots = mutableMapOf<Int, DaySlot>()
        for (i in 12..31) {
            val listOfTimeSlots = mutableListOf<TimeSlot>()
            for (j in 0..2) {
                val status = randomStatus.nextInt()
                val maintainStatus = randomMaintainStatus.nextInt()
                val slotStatus = if (i % 2 == 0 && j % 2 == 0 && maintainStatus == 1) {
                    Maintain
                } else {
                    if (status % 2 == 0)
                        Free
                    else
                        Booked
                }
                listOfTimeSlots.add(TimeSlot(generateID(i, j), generateStartTime(i, j), generateEndTime(i, j), slotStatus, false))
            }
            listOfDaySlots.put(i, DaySlot(listOfTimeSlots))
        }
        list.put(Calendar.MAY+1, MonthSlots(listOfDaySlots))
    }

    private fun generateEndTime(i: Int, j: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2019)
        cal.set(Calendar.MONTH, Calendar.MAY)
        cal.set(Calendar.DAY_OF_MONTH, i)
        cal.set(Calendar.HOUR_OF_DAY, j * gap + gap + 8)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    private fun generateStartTime(i: Int, j: Int): Date {
        val cal = Calendar.getInstance()
        cal.set(Calendar.YEAR, 2019)
        cal.set(Calendar.MONTH, Calendar.MAY)
        cal.set(Calendar.DAY_OF_MONTH, i)
        cal.set(Calendar.HOUR_OF_DAY, j * gap + 8)
        cal.set(Calendar.MINUTE, 0)
        cal.set(Calendar.SECOND, 0)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.time
    }

    private fun generateID(i: Int, j: Int): String {
        return "May_${i}_slot_$j"
    }

}