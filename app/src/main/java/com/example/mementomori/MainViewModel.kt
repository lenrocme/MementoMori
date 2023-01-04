package com.example.mementomori

import androidx.lifecycle.ViewModel
import com.example.mementomori.data.const.AgeGroups
import com.example.mementomori.data.const.DataPickDropDown
import com.example.mementomori.screen.mainChart.UserDataViewModel
import com.example.mementomori.screen.modal.userDataInput.UserInputViewModel

class MainViewModel(

    val userInputVm: UserInputViewModel = UserInputViewModel(),
    val userDataVM: UserDataViewModel = UserDataViewModel(),
): ViewModel() {
    val ageGroup: AgeGroups = AgeGroups()
    val dataPick: DataPickDropDown = DataPickDropDown()
}