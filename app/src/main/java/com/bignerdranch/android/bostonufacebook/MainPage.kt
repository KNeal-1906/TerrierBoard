package com.bignerdranch.android.bostonufacebook


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.bostonufacebook.Models.Data
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainPage : AppCompatActivity() {
    private lateinit var mGoogleSignInClient: GoogleSignInClient
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var dataRecyclerView: RecyclerView
    private lateinit var dataArrayList: ArrayList<Data>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth = FirebaseAuth.getInstance()
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        dataRecyclerView = findViewById(R.id.recyclerView)
        dataRecyclerView.layoutManager = LinearLayoutManager(this)
        dataRecyclerView.hasFixedSize()
        dataArrayList = arrayListOf<Data>()
        getData()


        binding.signOutBtn.setOnClickListener {
            signOutAndStartSignInActivity()
        }
        binding.goToPost.setOnClickListener {
            Toast.makeText(this,"you are going to the post page",Toast.LENGTH_SHORT)
            startActivity(Intent(this, MakePost::class.java))
        }

    }
    private fun getData(){

        database = FirebaseDatabase.getInstance().getReference("Data")
        database.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    for (dataSnapshot in snapshot.children){
                        val item = dataSnapshot.getValue(Data::class.java)
                        dataArrayList.add(item!!)
                    }
                    dataRecyclerView.adapter = DataAdapter(dataArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
    private fun signOutAndStartSignInActivity(){
        mAuth.signOut()

        mGoogleSignInClient.signOut().addOnCompleteListener(this) {
            // Optional: Update UI or show a message to the user
            val intent = Intent(this@MainPage, MainActivity::class.java)
            Toast.makeText(this, "User logged out succesfully", Toast.LENGTH_SHORT)
            startActivity(intent)
            finish()
        }
    }
}