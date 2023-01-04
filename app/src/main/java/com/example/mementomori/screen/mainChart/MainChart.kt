package com.example.mementomori.screen.mainChart

import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
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
import com.example.mementomori.screen.header.HeaderView
import com.example.mementomori.screen.modal.userDataInput.ModalUserDataInput
import com.example.mementomori.ui.theme.myColors

private const val itemPerRow = 15
private val chartPaddingTop = 45.dp

@Composable
fun MainChart(mainVm: MainViewModel) {
    mainVm.userDataVM.calc(mainVm.userInputVm)
    Box(modifier = Modifier
        .clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ){
            mainVm.userInputVm.isHeaderChartVis = false
            mainVm.userInputVm.isHeaderInfoVis = false
        }
        .fillMaxSize(),
        content = {
            Chart(mainVm)
            HeaderView(mainVm)

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
                    contentDescription = "Edit"
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
            .padding(top = chartPaddingTop)
            .verticalScroll(state),
        content = {
            if (mainVm.userDataVM.userLifeExpectation >= mainVm.userDataVM.userOldMonths) {
                for (row in 0..mainVm.userDataVM.userLifeExpectation / itemPerRow) {
                    Spacer(Modifier.height(3.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ) {
                        for (month in 1..itemPerRow) {
                            countMonth++
                            if (mainVm.userDataVM.userLifeExpectation >= countMonth)
                                ChartItem(mainVm, countMonth, mainVm.userDataVM.userLifeExpectation)
                        }
                    }
                }
            }
            else {
                for (row in 0 .. mainVm.userDataVM.userOldMonths / itemPerRow){
                    Spacer(Modifier.height(3.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start,
                    ){
                        for (month in 1..itemPerRow){
                            countMonth++
                            if(mainVm.userDataVM.userOldMonths >= countMonth)
                                ChartItem(mainVm, countMonth, mainVm.userDataVM.userOldMonths)
                        }
                    }
                }
            }
        })
}

@Composable
fun ChartItem(mainVm: MainViewModel, itemMonth: Int, countItems: Int) {
    Box(
        modifier = Modifier
            .size(percentWidth(1f) / itemPerRow)
            .background(color =
                if(itemMonth == mainVm.userDataVM.userOldMonths) {
                    Color.White
                } else {
                    if (
                        itemMonth <= mainVm.userDataVM.userLifeExpectation)
                        getColorBgForItemByAgeGroup(mainVm.ageGroup, itemMonth)
                    else
                        MaterialTheme.myColors.chart_bg_above
                }
                ),
        content = {
            if(countItems >= itemMonth) {
                Icon(
                    modifier = Modifier,
                    contentDescription = "Clear",
                    painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                    tint = if (itemMonth % 12 == 0)
                        Color.Red
                    else
                        if (itemMonth > mainVm.userDataVM.userLifeExpectation)
                            MaterialTheme.myColors.chart_above
                        else
                            Color.Blue,
                )
                if (mainVm.userDataVM.userOldMonths > itemMonth) {
                    Icon(
                        Icons.Default.Clear,
                        modifier = Modifier
                            .size(percentWidth(1f) / itemPerRow),
                        contentDescription = "Clear",
                        tint = MaterialTheme.myColors.chart_x
                    )
                }
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
