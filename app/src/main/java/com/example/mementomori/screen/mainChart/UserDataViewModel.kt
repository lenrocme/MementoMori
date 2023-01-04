package com.example.mementomori.screen.mainChart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.mementomori.data.const.DataPickDropDown
import com.example.mementomori.screen.modal.userDataInput.UserInputViewModel
import java.util.*

class UserDataViewModel() : ViewModel() {
    var userOldMonths: Int by mutableStateOf(720)
    var userOldYears: Double by mutableStateOf(60.0)
    var userLifeExpectation: Int by mutableStateOf(900)
    var userLifeExpectationYears: Int by mutableStateOf(75)
    var yearsByCountryAndSex: Double by mutableStateOf(80.0)
    var aboveAverageLifeExpect: Int by mutableStateOf(0)
    var remainMonths: Int by mutableStateOf(75)
    private var dataPicker = DataPickDropDown()

    fun calc(userInputVm: UserInputViewModel){
        calcLifeExpectation(userInputVm)
        calcActualPositionMonth(userInputVm)
        this.aboveAverageLifeExpect = this.userOldMonths - this.userLifeExpectation
        this.remainMonths = this.userLifeExpectation - this.userOldMonths
    }

    private fun calcLifeExpectation(userInputVm: UserInputViewModel) {
        val smokingCoeff = if(userInputVm.isSmoker)
             -5 * 12
        else
            0
        if(userInputVm.isMale)
            this.yearsByCountryAndSex = this.dataPicker.countryDict[userInputVm.country]!![2]
        else
            this.yearsByCountryAndSex = this.dataPicker.countryDict[userInputVm.country]!![1]

        this.userLifeExpectationYears = (this.yearsByCountryAndSex * 12).toInt()
        this.userLifeExpectation = userLifeExpectationYears + smokingCoeff
    }

    private fun calcActualPositionMonth(userInputVm: UserInputViewModel) {
        val calendar = Calendar.getInstance()
        val actualYear = calendar.get(Calendar.YEAR)
        val actualMonth = calendar.get(Calendar.MONTH) + 1
        val diffYears = actualYear - userInputVm.bornYear.toInt()
        val diffMonths = actualMonth - userInputVm.bornMonth.toInt()
        this.userOldMonths = diffYears * 12 + diffMonths
        this.setYearsOld(userOldMonths)
    }

    private fun setYearsOld(userOldMonths: Int) {
        this.userOldYears = userOldMonths / 12.0
    }
}