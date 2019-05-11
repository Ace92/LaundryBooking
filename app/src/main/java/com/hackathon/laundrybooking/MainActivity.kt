package com.hackathon.laundrybooking

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.tvattroom.BookStatus
import com.example.tvattroom.Booked
import com.example.tvattroom.Free
import com.example.tvattroom.Maintain
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(), CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener {
    val repository = DummyRepository()
    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {

    }

    override fun onCalendarOutOfRange(calendar: Calendar?) {

    }

    override fun onYearChange(year: Int) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView.setOnCalendarSelectListener(this)
        calendarView.setOnYearChangeListener(this)
        initData()
    }

    protected fun initData() {
        val year = calendarView.getCurYear()
//        val month = calendarView.getCurMonth()

        val map = mutableMapOf<String, Calendar>()
        repository.getAvaliableSlots().forEach { month, slots ->
            slots.daySlots.forEach { day, daySlots ->

                map.put(getSchemeCalendar(year, month, day, daySlots).toString(),
                        getSchemeCalendar(year, month, day, daySlots))
            }
        }
        calendarView.setSchemeDate(map)
    }

/*
    map.put(getSchemeCalendar(year, month, 3, -0xbf24db, "假").toString(),
    getSchemeCalendar(year, month, 3, -0xbf24db, "假"))
    map.put(getSchemeCalendar(year, month, 6, -0x196ec8, "事").toString(),
    getSchemeCalendar(year, month, 6, -0x196ec8, "事"))
    map.put(getSchemeCalendar(year, month, 9, -0x20ecaa, "议").toString(),
    getSchemeCalendar(year, month, 9, -0x20ecaa, "议"))
    map.put(getSchemeCalendar(year, month, 13, -0x123a93, "记").toString(),
    getSchemeCalendar(year, month, 13, -0x123a93, "记"))
    map.put(getSchemeCalendar(year, month, 14, -0x123a93, "记").toString(),
    getSchemeCalendar(year, month, 14, -0x123a93, "记"))
    map.put(getSchemeCalendar(year, month, 15, -0x5533bc, "假").toString(),
    getSchemeCalendar(year, month, 15, -0x5533bc, "假"))
    map.put(getSchemeCalendar(year, month, 18, -0x43ec10, "记").toString(),
    getSchemeCalendar(year, month, 18, -0x43ec10, "记"))
    map.put(getSchemeCalendar(year, month, 22, -0x20ecaa, "议").toString(),
    getSchemeCalendar(year, month, 22, -0x20ecaa, "议"))
    map.put(getSchemeCalendar(year, month, 25, -0xec5310, "假").toString(),
    getSchemeCalendar(year, month, 25, -0xec5310, "假"))
    map.put(getSchemeCalendar(year, month, 27, -0xec5310, "多").toString(),
    getSchemeCalendar(year, month, 27, -0xec5310, "多"))
    //此方法在巨大的数据量上不影响遍历性能，推荐使用*/


    private fun getSchemeCalendar(year: Int, month: Int, day: Int, daySlot: DaySlot): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = -0x123a93//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = "假"
        daySlot.list.forEach { timeSlot ->
            calendar.addScheme(getTimeSlotColor(timeSlot.bookStatus), timeSlot.id)
        }
        return calendar
    }

    fun getTimeSlotColor(bookStatus: BookStatus): Int {
        return when (bookStatus) {
            Free -> resources.getColor(R.color.colorTimeSlot_Free)
            Booked -> resources.getColor(R.color.colorTimeSlot_Booked)
            Maintain -> resources.getColor(R.color.colorTimeSlot_Unavailable)
        }
    }


}
