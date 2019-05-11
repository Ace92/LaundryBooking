package com.hackathon.laundrybooking


import android.app.Dialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuItem
import android.view.Window
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.tvattroom.*
import com.hackathon.laundrybooking.full.ArticleAdapter
import com.hackathon.laundrybooking.full.GroupItemDecoration
import com.hackathon.laundrybooking.full.ItemClick
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity(), CalendarView.OnCalendarSelectListener,
        CalendarView.OnYearChangeListener, ItemClick {
    override fun onItemClick(timeSlot: TimeSlot) {
        showDialog("Book time", timeSlot)

    }

    val repository = DummyRepository()
    val slotMap: LinkedHashMap<String, List<TimeSlot>> = LinkedHashMap<String, List<TimeSlot>>()
    val list = LinkedHashMap<Int, MonthSlots>()
    lateinit var adapter: ArticleAdapter

    override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
        if (calendar != null) {
            slotMap.clear()
            val monthSlots = list[calendar.month]
            monthSlots?.let {
                val daySlots = it.daySlots[calendar.day]?.list
                daySlots?.let {
                    slotMap.put("${calendar.day}/${calendar.month}/19", daySlots)
                    adapter.notifyDataSetChange()
                    recyclerView.notifyDataSetChanged()
                    if (calendarLayout.isExpand()) {
                        calendarLayout.shrink()
                        return;
                    }
                }
            }
            if (monthSlots == null) {
                if (!calendarLayout.isExpand()) {
                    calendarLayout.expand()
                    return;
                }
                slotMap.clear()
                adapter.notifyDataSetChange()
                recyclerView.notifyDataSetChanged()
            }
        } else {
            if (!calendarLayout.isExpand()) {
                calendarLayout.expand()
                return;
            }
            slotMap.clear()
            adapter.notifyDataSetChange()
            recyclerView.notifyDataSetChanged()
        }
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
        list.clear()
        list.putAll(repository.getAvaliableSlots())

        list.forEach { month, slots ->
            slots.daySlots.forEach { day, daySlots ->

                map.put(getSchemeCalendar(year, month, day, daySlots).toString(),
                        getSchemeCalendar(year, month, day, daySlots))
            }
        }
        calendarView.setSchemeDate(map)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(GroupItemDecoration<String, TimeSlot>())
        adapter = ArticleAdapter(this, slotMap)
        adapter.setItemClickListener(this)
        recyclerView.adapter = adapter
        recyclerView.notifyDataSetChanged()
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
            calendar.addScheme(getTimeSlotColor(resources, timeSlot.bookStatus), timeSlot.id)
        }
        return calendar
    }

    companion object {
        fun getTimeSlotColor(resources: Resources, bookStatus: BookStatus): Int {
            return when (bookStatus) {
                Free -> resources.getColor(R.color.colorTimeSlot_Free)
                Booked -> resources.getColor(R.color.colorTimeSlot_Booked)
                Maintain -> resources.getColor(R.color.colorTimeSlot_Unavailable)
            }
        }
    }

    private fun showDialog(title: String, timeSlot: TimeSlot) {
        val dialog = Dialog(this)
        dialog .requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog .setCancelable(false)
        dialog .setContentView(R.layout.custom_dialog)
        val body = dialog.findViewById<TextView>(R.id.txt_dia) as TextView
        body.text = title
        val yesBtn = dialog.findViewById<Button>(R.id.btn_yes) as Button
        val noBtn = dialog .findViewById<Button>(R.id.btn_no) as TextView
        yesBtn.setOnClickListener {
            dialog .dismiss()
            Toast.makeText(this, "You have booked ", Toast.LENGTH_SHORT).show()

        }
        noBtn.setOnClickListener { dialog .dismiss() }
        dialog .show()

    }

}