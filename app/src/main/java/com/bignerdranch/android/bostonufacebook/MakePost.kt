package com.bignerdranch.android.bostonufacebook

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import com.bignerdranch.android.bostonufacebook.Models.Data
import com.bignerdranch.android.bostonufacebook.databinding.ActivityMakePostBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.io.ByteArrayOutputStream
import java.lang.Exception
import kotlin.random.Random

class MakePost : AppCompatActivity() {
    private lateinit var binding: ActivityMakePostBinding
    private lateinit var database: DatabaseReference
    var sImage:String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMakePostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.postBtn.setOnClickListener {
            Log.d("MakePostActivity", "Post button clicked")
            var itemId= Random.nextInt()
            val name = binding.titleInput.text.toString()
            val comment = binding.bodyInput.text.toString()
            database= FirebaseDatabase.getInstance().getReference("Data")
            val Data = Data(name, comment,sImage)
            database.child(itemId.toString()).setValue(Data).addOnCompleteListener{
                binding.titleInput.text.clear()

                binding.bodyInput.text.clear()

                Toast.makeText(this, "Succesfully created an entry", Toast.LENGTH_SHORT).show()
            }.addOnFailureListener {

                binding.titleInput.text.clear()
                binding.bodyInput.text.clear()
                Toast.makeText(this, "You are a failure", Toast.LENGTH_SHORT)}
        }


    }
    fun upload(view: View){

    }
    fun pickImage(view: View){
        var pickImageIntent= Intent(Intent.ACTION_GET_CONTENT)
        pickImageIntent.setType("image/*")
        ActivityResultLauncher.launch(pickImageIntent)

    }
    private val ActivityResultLauncher =registerForActivityResult<Intent, ActivityResult>(
        ActivityResultContracts.StartActivityForResult()
    ){result :ActivityResult ->
        if(result.resultCode== RESULT_OK){
            val uri = result.data!!.data
            try {
                val inputStream = contentResolver.openInputStream(uri!!)
                val myBitmap = BitmapFactory.decodeStream(inputStream)
                val stream = ByteArrayOutputStream()
                myBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
                val bytes = stream.toByteArray()
                sImage= Base64.encodeToString(bytes,Base64.DEFAULT)
                binding.imageView.setImageBitmap(myBitmap)
                Toast.makeText(this, "Image selected", Toast.LENGTH_SHORT)
                inputStream!!.close()

            }
            catch (ex: Exception){
                Toast.makeText(this, ex.message.toString(), Toast.LENGTH_SHORT)
            }
        }
    }

}