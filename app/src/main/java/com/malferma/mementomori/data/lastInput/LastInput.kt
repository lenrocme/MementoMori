package com.malferma.mementomori.data.lastInput

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "lastInput")
data class LastInput (

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Int = 1,

    @ColumnInfo(name = "country")
    val country: String = "Afghanistan",

    @ColumnInfo(name = "bornMonth")
    val bornMonth: String = "01",

    @ColumnInfo(name = "bornYear")
    val bornYear: String = "1980",

    @ColumnInfo(name = "isMale")
    val isMale: Boolean = true,

    @ColumnInfo(name = "isSmoker")
    val isSmoker: Boolean = false,
)