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
    }

}