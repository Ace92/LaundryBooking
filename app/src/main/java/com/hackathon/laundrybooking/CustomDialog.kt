package com.hackathon.laundrybooking

import android.os.Bundle
import android.app.Activity
import android.app.Dialog
import android.view.View
import android.view.Window
import android.widget.Button



/**
 * Created by s26150 on 2019-05-11.
 */
class CustomDialog(var c: Activity)// TODO Auto-generated constructor stub
    : Dialog(c), android.view.View.OnClickListener {
    var yes: Button? = null
    var no:Button? = null

    override fun onCreate(savedInstanceState: Bundle) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.custom_dialog)
        yes = (findViewById<View>(R.id.btn_yes) as Button)
        no = (findViewById<View>(R.id.btn_no) as Button)
        yes?.setOnClickListener(this)
        no?.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_yes -> c.finish()
            R.id.btn_no -> dismiss()
            else -> {
            }
        }
        dismiss()
    }
}