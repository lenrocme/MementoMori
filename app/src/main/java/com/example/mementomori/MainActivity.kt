package com.example.mementomori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mementomori.screen.mainChart.MainChart
import com.example.mementomori.screen.mainChart.UserDataViewModel
import com.example.mementomori.screen.modal.userDataInput.UserInputViewModel
import com.example.mementomori.ui.theme.CustomMaterialTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainVm: MainViewModel
    private lateinit var userDataVm: UserDataViewModel
    private lateinit var userInputVm: UserInputViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.userDataVm = UserDataViewModel()
        this.userInputVm = UserInputViewModel()
        this.mainVm = MainViewModel(userDataVm, userInputVm)

        setContent {
            CustomMaterialTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main(mainVm)
                }
            }
        }
    }
}

@Composable
fun Main(mainVm: MainViewModel) {
    MainChart(mainVm)
}
/*
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MementoMoriTheme {
        Greeting()
    }
}*/