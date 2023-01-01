package com.example.mementomori

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mementomori.screen.MainChart
import com.example.mementomori.screen.UserDataViewModel
import com.example.mementomori.ui.theme.MementoMoriTheme

class MainActivity : ComponentActivity() {
    private lateinit var mainVm: MainViewModel
    private lateinit var userDataVm: UserDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.userDataVm = UserDataViewModel()
        this.mainVm = MainViewModel(userDataVm)

        setContent {
            MementoMoriTheme {
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