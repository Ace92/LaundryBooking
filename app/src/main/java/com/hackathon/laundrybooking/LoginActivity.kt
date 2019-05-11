package com.hackathon.laundrybooking

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.Exception

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginButton.setOnClickListener {
            try {
                openBankidApp()
            }
            catch (e: Exception){
//                openMainActivity()
            }
        }
    }

    fun openBankidApp() {
        val intent = Intent()
        intent.setPackage("com.bankid.bus")
        intent.setAction(Intent.ACTION_VIEW)
//            setData(Uri.parse("bankid:///?autostarttoken=token&redirect=null "))
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        openMainActivity()
    }

    fun openMainActivity(){
        startActivity(Intent(this, MainActivity::class.java))
    }
}
