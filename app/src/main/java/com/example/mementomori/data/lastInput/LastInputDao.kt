package com.example.mementomori.data.lastInput

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface LastInputDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addLastInput(lastInput: LastInput)

    @Query("SELECT * FROM LastInput")
    fun readAllData(): LiveData<List<LastInput>>

    @Update
    fun updateLastInput(entity: LastInput)
}