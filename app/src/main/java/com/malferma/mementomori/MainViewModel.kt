package com.malferma.mementomori

import androidx.lifecycle.ViewModel
import com.malferma.mementomori.data.const.AgeGroups
import com.malferma.mementomori.data.const.DataPickDropDown
import com.malferma.mementomori.screen.mainChart.UserDataViewModel
import com.malferma.mementomori.screen.modal.userDataInput.UserInputViewModel

class MainViewModel(

    val userInputVm: UserInputViewModel = UserInputViewModel(),
    val userDataVM: UserDataViewModel = UserDataViewModel(),
): ViewModel() {
    val ageGroup: AgeGroups = AgeGroups()
    val dataPick: DataPickDropDown = DataPickDropDown()
}