package com.example.mementomori.screen.mainChart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mementomori.data.const.DataPickDropDown
import com.example.mementomori.screen.modal.userDataInput.UserInputViewModel

class UserDataViewModel() : ViewModel() {
    var userOldMonths: Int by mutableStateOf(720)
    var userLifeExpectation: Int by mutableStateOf(900)
    var userLifeExpectationYears: Int by mutableStateOf(75)
    var yearsByCountryAndSex: Double by mutableStateOf(80.0)
    private var dataPicker = DataPickDropDown()


    fun calcMonthsLifeExpect(userInputVm: UserInputViewModel) {
        var smokingCoeff = if(userInputVm.isSmoker)
             -10 * 12
        else
            0
        if(userInputVm.isMale)
            this.yearsByCountryAndSex = this.dataPicker.countryDict[userInputVm.country]!![2]
        else
            this.yearsByCountryAndSex = this.dataPicker.countryDict[userInputVm.country]!![1]

        this.userLifeExpectationYears = (this.yearsByCountryAndSex * 12).toInt()
        this.userLifeExpectation = userLifeExpectationYears + smokingCoeff
    }
}