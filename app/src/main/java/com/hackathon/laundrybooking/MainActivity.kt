package com.hackathon.laundrybooking


import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
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

        val map = mutableMapOf<String, Calendar>()
        repository.getAvaliableSlots().forEach { month, slots ->
            slots.daySlots.forEach { day, daySlots ->

                map.put(getSchemeCalendar(year, month, day, daySlots).toString(),
                        getSchemeCalendar(year, month, day, daySlots))
            }
        }
        calendarView.setSchemeDate(map)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu to use in the action bar
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        val intentSettings = Intent(this, SettingsActivity::class.java)
        val intentLogout = Intent(this, LoginActivity::class.java)

        when (item.itemId) {
            R.id.settings -> {
                startActivity(intentSettings)
            }
            R.id.logout -> {
                startActivity(intentLogout)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun getSchemeCalendar(year: Int, month: Int, day: Int, daySlot: DaySlot): Calendar {

        val calendar = Calendar()
        calendar.year = year
        calendar.month = month
        calendar.day = day
        calendar.schemeColor = -0x123a93//如果单独标记颜色、则会使用这个颜色
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