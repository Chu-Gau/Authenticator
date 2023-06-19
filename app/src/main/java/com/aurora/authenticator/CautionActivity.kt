package com.aurora.authenticator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class CautionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caution)
    }

    fun next(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}