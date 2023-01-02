package com.example.mementomori.screen.mainChart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mementomori.MainViewModel
import com.example.mementomori.helper.percentWidth
import com.example.mementomori.R
import com.example.mementomori.data.const.AgeGroups
import com.example.mementomori.screen.modal.userDataInput.ModalUserDataInput

/*
@Preview(showBackground = true)
@Composable
fun MainChartPreview() {
    MainChart(MainViewModel(UserDataViewModel()))
}
*/

private const val itemPerRow = 15

@Composable
fun MainChart(mainVm: MainViewModel) {
    Box(modifier = Modifier
        .fillMaxSize(),
        content = {
            Chart(mainVm)

            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
                onClick = {
                    mainVm.userInputVm.isModalVisible = true
                    //Toast.makeText(contextForToast, "Click", Toast.LENGTH_SHORT).show()
                }) {
                Icon(
                    imageVector = Icons.Filled.Edit,
                    contentDescription = "Add"
                )
            }

            if (mainVm.userInputVm.isModalVisible)
                ModalUserDataInput(mainVm)
        }
    )
}

@Composable
fun Chart(mainVm: MainViewModel, countMonths: Int = 12 * 80) {
    var months = countMonths
    var countMonth = 0
    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state),
        content = {
            for (row in 0 until months / itemPerRow){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    for (month in 1..itemPerRow){
                        countMonth++
                        ChartItem(mainVm, countMonth)
                    }
                }
                Spacer(Modifier.height(3.dp))
            }
        })
}

@Composable
fun ChartItem(mainVm: MainViewModel, month: Int) {
    val sizeItem = 100 / itemPerRow / 100
    Box(
        modifier = Modifier
            .background(color = getColorBgForItemByAgeGroup(mainVm.ageGroup, month)),
        content = {
            Icon(
                modifier = Modifier
                    .size(percentWidth(1f) / itemPerRow - 1.dp),
                contentDescription = "Clear",
                painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                tint = if (month % 12 == 0)
                    Color.Red
                else
                    Color.Blue,
            )
            if (mainVm.userDataVM.userOldMonths > month) {
                Icon(
                    Icons.Default.Clear,
                    modifier = Modifier
                        .size(percentWidth(1f) / itemPerRow - 1.dp),
                    contentDescription = "Clear",
                    tint = Color.Red
                )
            }
    })
}

@Composable
private fun getColorBgForItemByAgeGroup(ageGroup: AgeGroups, month: Int): Color {
    return when (month) {
        in ageGroup.noneAdult until ageGroup.adult -> Color.LightGray
        in ageGroup.adult until ageGroup.middleAgeAdult -> Color.Green
        in ageGroup.middleAgeAdult until ageGroup.lateAdult -> Color.Blue
        in ageGroup.lateAdult until ageGroup.senior -> Color.Red
        in ageGroup.senior until ageGroup.passive -> Color.Yellow
        else -> Color.DarkGray
    }
}
