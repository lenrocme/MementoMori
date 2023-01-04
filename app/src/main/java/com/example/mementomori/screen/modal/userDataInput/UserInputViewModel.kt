package com.example.mementomori.screen.modal.userDataInput

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserInputViewModel: ViewModel() {
    var isModalVisible: Boolean by mutableStateOf(false)
    var isMale: Boolean by mutableStateOf(true)
    var bornYear: String by mutableStateOf("1980")
    var bornMonth: String by mutableStateOf("01")
    var country: String by mutableStateOf("Afghanistan")
    var isSmoker: Boolean by mutableStateOf(false)

    fun setData(
        country: String,
        bornMonth: String,
        bornYear: String,
        isMale: Boolean,
        isSmoker: Boolean
    ) {
        this.country = country
        this.bornMonth = bornMonth
        this.bornYear = bornYear
        this.isMale = isMale
        this.isSmoker = isSmoker
    }
}