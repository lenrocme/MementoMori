package com.malferma.mementomori.screen.modal.userDataInput

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.malferma.mementomori.data.lastInput.LastInput

class UserInputViewModel: ViewModel() {
    var isModalVisible: Boolean by mutableStateOf(false)
    var isHeaderChartVis: Boolean by mutableStateOf(false)
    var isHeaderInfoVis: Boolean by mutableStateOf(false)


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
        this.country    = country
        this.bornMonth  = bornMonth
        this.bornYear   = bornYear
        this.isMale     = isMale
        this.isSmoker   = isSmoker
    }

    fun getLastInput(): LastInput {
        return LastInput(
            id          = 1,
            country     = this.country,
            bornMonth   = this.bornMonth,
            bornYear    = this.bornYear,
            isMale      = this.isMale,
            isSmoker    = this.isSmoker
        )
    }

    fun setDataByUsingRoom(lastInput: LastInput) {
        this.country    = lastInput.country
        this.bornMonth  = lastInput.bornMonth
        this.bornYear   = lastInput.bornYear
        this.isMale     = lastInput.isMale
        this.isSmoker   = lastInput.isSmoker
    }
}