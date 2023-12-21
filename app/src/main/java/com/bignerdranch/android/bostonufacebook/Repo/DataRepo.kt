package com.bignerdranch.android.bostonufacebook.Repo

import androidx.lifecycle.MutableLiveData
import com.bignerdranch.android.bostonufacebook.Models.Data
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import java.lang.Exception

class DataRepo {
    //Get the dabase object and get the head node, which is ""Data""
    private val databaseReference :DatabaseReference= FirebaseDatabase.getInstance().getReference("Data")
    @Volatile private var INSTANCE: DataRepo?=null

    fun getInstance(): DataRepo{
        return INSTANCE?: synchronized(this){
            val instance = DataRepo()
            INSTANCE= instance
            instance
        }


    }
    fun loadData(DataList: MutableLiveData<List<Data>>){
        databaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                try {
                    val _dataList : List<Data> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Data::class.java)!!
                    }
                    DataList.postValue(_dataList)
                }
                catch (e:Exception){

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        )


    }

}