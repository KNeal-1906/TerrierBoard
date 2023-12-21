package com.bignerdranch.android.bostonufacebook.Models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bignerdranch.android.bostonufacebook.Repo.DataRepo

class DataViewModel: ViewModel() {
    private val repository  : DataRepo
    private val _allData = MutableLiveData<List<Data>>()
    val allUser : LiveData<List<Data>> = _allData

    init {
        repository=DataRepo().getInstance()
        repository.loadData(_allData)
    }



}