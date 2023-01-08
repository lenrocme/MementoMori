package com.malferma.mementomori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.malferma.mementomori.data.lastInput.LastInput
import com.malferma.mementomori.data.lastInput.LastInputViewModel
import com.malferma.mementomori.screen.mainChart.MainChart
import com.malferma.mementomori.screen.mainChart.UserDataViewModel
import com.malferma.mementomori.screen.modal.userDataInput.UserInputViewModel
import com.malferma.mementomori.ui.theme.CustomMaterialTheme

class MainActivity : ComponentActivity() {
    private lateinit var mUserForm: LastInputViewModel
    private lateinit var mainVm: MainViewModel
    private lateinit var userDataVm: UserDataViewModel
    private lateinit var userInputVm: UserInputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.mUserForm = ViewModelProvider(this)[LastInputViewModel::class.java]
        this.userInputVm = UserInputViewModel()
        this.userDataVm = UserDataViewModel()
        this.mainVm = MainViewModel(userInputVm, userDataVm)

        // store default data to the db, on the first load
        this.mUserForm.readAllData.observe(this) { it ->
            if (it.isEmpty()) {
                this.initDefaultDataLastInput()
            } else {
                this.userInputVm.setDataByUsingRoom(it[0])
                this.userDataVm.calc(this.userInputVm)
            }
        }

        setContent {
            CustomMaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(mainVm, mUserForm)
                }
            }
        }
    }

    private fun initDefaultDataLastInput() {
        this.mUserForm.add(LastInput())
    }
}

@Composable
fun Main(mainVm: MainViewModel, mUserForm: LastInputViewModel) {
    MainChart(mainVm, mUserForm)
}