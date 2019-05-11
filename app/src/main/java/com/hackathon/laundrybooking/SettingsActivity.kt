package com.hackathon.laundrybooking

import android.app.Dialog
import android.app.PendingIntent.getActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.Window
import android.widget.*


/**
 * Created by s26150 on 2019-05-11.
 */
class SettingsActivity: AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

       showDialog("TEST")

    }

    private fun showDialog(title: String) {
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
        }
        noBtn.setOnClickListener { dialog .dismiss() }
        dialog .show()

    }


/*
    fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val numberPicker = NumberPicker(this)
        numberPicker.setMinValue(24);
        numberPicker.setMaxValue(64);

        val builder = AlertDialog.Builder(this)
        builder.setView(numberPicker)
        builder.setTitle("Changing the Hue")
        builder.setMessage("Choose a value :")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which -> numberPicker.value })
        builder.setNegativeButton("CANCEL", DialogInterface.OnClickListener { dialog, which ->  })
        builder.create()
        return builder.show()
    }
*/

}