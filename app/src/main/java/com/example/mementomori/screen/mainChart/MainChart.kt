package com.example.mementomori.screen.mainChart

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentCompositionLocalContext
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mementomori.MainViewModel
import com.example.mementomori.helper.percentWidth
import com.example.mementomori.R
import com.example.mementomori.data.const.AgeGroups
import com.example.mementomori.screen.modal.userDataInput.ModalUserDataInput
import com.example.mementomori.ui.theme.myColors

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
    mainVm.userDataVM.setData(mainVm.userInputVm)
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
fun Chart(mainVm: MainViewModel) {
    var countMonth = 0
    val state = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state),
        content = {
            for (row in 0 .. mainVm.userDataVM.userLifeExpectation / itemPerRow){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Start,
                ){
                    for (month in 1..itemPerRow){
                        countMonth++
                        if(mainVm.userDataVM.userLifeExpectation >= countMonth)
                            ChartItem(mainVm, countMonth)
                    }
                }
                Spacer(Modifier.height(3.dp))
            }
        })
}

@Composable
fun ChartItem(mainVm: MainViewModel, itemMonth: Int) {
    val sizeItem = 100 / itemPerRow / 100
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .background(color = getColorBgForItemByAgeGroup(mainVm.ageGroup, itemMonth)),
        content = {
            Icon(
                modifier = Modifier
                    .size(percentWidth(1f) / itemPerRow),
                contentDescription = "Clear",
                painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                tint = if (itemMonth % 12 == 0)
                    Color.Red
                else
                    Color.Blue,
            )
            if (mainVm.userDataVM.userOldMonths > itemMonth) {
                Icon(
                    Icons.Default.Clear,
                    modifier = Modifier
                        .size(percentWidth(1f) / itemPerRow - 1.dp),
                    contentDescription = "Clear",
                    tint = MaterialTheme.myColors.chart_x
                )
            }
    })
}

@Composable
private fun getColorBgForItemByAgeGroup(ageGroup: AgeGroups, month: Int): Color {
    return when (month) {
        in ageGroup.noneAdult until ageGroup.adult -> MaterialTheme.myColors.chart_1
        in ageGroup.adult until ageGroup.middleAgeAdult -> MaterialTheme.myColors.chart_2
        in ageGroup.middleAgeAdult until ageGroup.lateAdult -> MaterialTheme.myColors.chart_3
        in ageGroup.lateAdult until ageGroup.senior -> MaterialTheme.myColors.chart_4
        in ageGroup.senior until ageGroup.passive -> MaterialTheme.myColors.chart_5
        else -> MaterialTheme.myColors.chart_6
    }
}
