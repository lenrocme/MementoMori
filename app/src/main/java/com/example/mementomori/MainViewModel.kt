package com.example.mementomori

import androidx.lifecycle.ViewModel
import com.example.mementomori.data.const.AgeGroups
import com.example.mementomori.data.const.DataPickDropDown
import com.example.mementomori.screen.mainChart.UserDataViewModel
import com.example.mementomori.screen.modal.userDataInput.UserInputViewModel

class MainViewModel(
    val userDataVM: UserDataViewModel = UserDataViewModel(),
    val userInputVm: UserInputViewModel = UserInputViewModel(),
): ViewModel() {
    val ageGroup: AgeGroups = AgeGroups()
    val dataPick: DataPickDropDown = DataPickDropDown()
}