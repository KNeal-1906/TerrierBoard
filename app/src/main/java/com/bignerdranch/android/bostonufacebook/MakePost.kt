package com.bignerdranch.android.bostonufacebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bignerdranch.android.bostonufacebook.Models.Data
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMainBinding
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMakePostBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MakePost : AppCompatActivity() {
    private lateinit var binding: ActivityMakePostBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePostBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_make_post)

        binding.postBtn.setOnClickListener {val name = binding.titleInput.text.toString()
            val comment = binding.bodyInput.text.toString()
            database= FirebaseDatabase.getInstance().getReference("Data")
            val Data = Data(name, comment)
            database.child(name).setValue(Data).addOnCompleteListener{
                binding.titleInput.text.clear()
                binding.bodyInput.text.clear()
                Toast.makeText(this, "Succesfully created an entry", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {
                binding.titleInput.text.clear()
                binding.bodyInput.text.clear()
                Toast.makeText(this, "You are a failure", Toast.LENGTH_SHORT)}
        }


    }
}