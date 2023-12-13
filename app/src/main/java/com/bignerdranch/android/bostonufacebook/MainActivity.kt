package com.bignerdranch.android.bostonufacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        val guestButton = findViewById<Button>(R.id.guest_btn)
        guestButton.setOnClickListener {
            // Switch to R.layout.page when the button is clicked
            setContentView(R.layout.activity_main)
        }

    }
}