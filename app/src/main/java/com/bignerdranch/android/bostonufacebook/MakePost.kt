package com.bignerdranch.android.bostonufacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMainBinding
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMakePostBinding
import com.google.firebase.database.DatabaseReference

class MakePost : AppCompatActivity() {
    private lateinit var binding: ActivityMakePostBinding
        private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePostBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_make_post)

    }
}