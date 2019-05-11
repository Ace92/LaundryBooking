package com.hackathon.laundrybooking

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity(),  CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener {
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
        val month = calendarView.getCurMonth()

        val map = mutableMapOf<String, Calendar>()
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
        //此方法在巨大的数据量上不影响遍历性能，推荐使用
        calendarView.setSchemeDate(map)
    }

    private fun getSchemeCalendar(year: Int, month: Int, day: Int, color: Int, text: String): Calendar {
        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = color//如果单独标记颜色、则会使用这个颜色
        calendar.scheme = text
        calendar.addScheme(color, "timeslot1")
        calendar.addScheme(if (day % 2 == 0) -0xff3300 else -0x2ea012, "timeslot2")
        calendar.addScheme(if (day % 7 == 0) -0x000000 else -0xBE471F, "timeslot3")
        calendar.addScheme(if (day % 3 == 0) -0x1fbe47 else -0xBE471F, "timeslot4")
        calendar.addScheme(if (day % 5 == 0) -0x1fbe47 else -0xBE471F, "timeslot5")
        return calendar
    }
}
