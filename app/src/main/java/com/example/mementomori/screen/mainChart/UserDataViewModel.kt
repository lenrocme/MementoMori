package com.example.mementomori.screen.mainChart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UserDataViewModel: ViewModel() {
    var userOldMonths: Int by mutableStateOf(403)
}