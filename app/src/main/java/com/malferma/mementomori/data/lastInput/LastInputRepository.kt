package com.malferma.mementomori.data.lastInput

import androidx.lifecycle.LiveData

class LastInputRepository(private val lastInputDao: LastInputDao) {

    val readAllData : LiveData<List<LastInput>> = lastInputDao.readAllData()

    suspend fun addLastInput(lastInput: LastInput) {
        lastInputDao.addLastInput(lastInput)
    }

    fun updateLastInput(lastInput: LastInput) {
        lastInputDao.updateLastInput(lastInput)
    }
}