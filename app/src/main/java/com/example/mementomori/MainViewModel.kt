package com.example.mementomori

import androidx.lifecycle.ViewModel
import com.example.mementomori.data.const.AgeGroups
import com.example.mementomori.screen.mainChart.UserDataViewModel

class MainViewModel(
    val userDataVM: UserDataViewModel = UserDataViewModel(),
    val ageGroup: AgeGroups = AgeGroups(),
): ViewModel() {

}