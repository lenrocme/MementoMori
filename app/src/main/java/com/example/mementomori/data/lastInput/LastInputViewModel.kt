package com.example.mementomori.data.lastInput

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.mementomori.data.database.MementoMoriDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LastInputViewModel(application: Application): AndroidViewModel(application) {

    val readAllData: LiveData<List<LastInput>>
    private val repository: LastInputRepository

    init {
        val lastInputDao = MementoMoriDb.getDatabase(application).lastInputDao()
        repository = LastInputRepository(lastInputDao)
        readAllData = repository.readAllData
    }

    fun add(lastInput: LastInput){
        viewModelScope.launch(Dispatchers.IO){
            repository.addLastInput(lastInput)
        }
    }

    fun update(lastInput: LastInput){
        viewModelScope.launch(Dispatchers.IO){
            repository.updateLastInput(lastInput)
        }
    }
}