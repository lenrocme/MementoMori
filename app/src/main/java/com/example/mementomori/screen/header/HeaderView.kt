package com.example.mementomori.screen.header

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mementomori.MainViewModel
import com.example.mementomori.R
import com.example.mementomori.helper.percentHeight
import com.example.mementomori.helper.percentWidth
import com.example.mementomori.ui.theme.AgeGroup
import com.example.mementomori.ui.theme.HeaderContainer
import com.example.mementomori.ui.theme.myColors

@Composable
fun HeaderView(mainVm: MainViewModel) {
    val focusManager = LocalFocusManager.current
    Surface(
        modifier = Modifier
            .shadow(elevation = 10.dp, shape = RectangleShape)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                )
            )
            .fillMaxWidth()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }
            .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                //mUiTaxViewModel.isTaxReportExtended = !mUiTaxViewModel.isTaxReportExtended
                mainVm.userInputVm.isHeaderChartVis = false
                mainVm.userInputVm.isHeaderInfoVis = false
            }
            .background(color = MaterialTheme.myColors.bgHeader),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .background(color = MaterialTheme.myColors.bgHeader),
        ) {
            Spacer( // spacer for translucent action bar
                modifier = Modifier
                    .height(0.dp)
                    .fillMaxWidth()
            )
            Row(
                modifier = Modifier,
                    //.padding(bottom = 5.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            mainVm.userInputVm.isHeaderInfoVis = false
                            mainVm.userInputVm.isHeaderChartVis = true
                        }
                        .padding(5.dp)
                        .size(35.dp)
                        .rotate(90f),
                    painter = painterResource(id = R.drawable.ic_baseline_bar_chart_24),
                    contentDescription = "stats",
                    tint = MaterialTheme.myColors.headerItems
                )
                Text(
                    modifier = Modifier
                        .weight(1f ),
                    text = "${mainVm.userDataVM.userOldMonths} / ${mainVm.userDataVM.userLifeExpectation}",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.h2,
                    color = MaterialTheme.myColors.headerItems,
                )
                Icon(
                    modifier = Modifier
                        .clickable(
                            interactionSource = MutableInteractionSource(),
                            indication = null
                        ) {
                            mainVm.userInputVm.isHeaderChartVis = false
                            mainVm.userInputVm.isHeaderInfoVis = true
                        }
                        .padding(5.dp)
                        .size(35.dp),
                    painter = painterResource(id = R.drawable.ic_outline_info_24),
                    contentDescription = "info",
                    tint = MaterialTheme.myColors.headerItems
                )
            }
            if (mainVm.userInputVm.isHeaderChartVis) {
                HeaderStatsChart(mainVm)
            }
            if (mainVm.userInputVm.isHeaderInfoVis) {
                HeaderInfoChart(mainVm)
            }
        }
    }

}

@Composable
private fun HeaderStatsChart(mainVm: MainViewModel) {
    Column {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = if (mainVm.userDataVM.aboveAverageLifeExpect <= 0)
                "Remain: ${mainVm.userDataVM.remainMonths} months(Leafs)"
            else
                "Above avg: ${mainVm.userDataVM.aboveAverageLifeExpect}\nYou live already ${mainVm.userDataVM.aboveAverageLifeExpect} months(Leafs) above average",
            style = MaterialTheme.typography.HeaderContainer,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Spent: ${mainVm.userDataVM.userOldMonths} months(Leafs)",
            style = MaterialTheme.typography.HeaderContainer,
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = "Your life expectancy is ${mainVm.userDataVM.userLifeExpectation} months(Leafs)",
            style = MaterialTheme.typography.HeaderContainer,
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun HeaderInfoChart(mainVm: MainViewModel) {
    Column() {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            text = "info"
        )
        AgeGroup()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun AgeGroup() {
    Column(
        modifier = Modifier
            .width(percentWidth(.7f))
            .height(percentHeight(.6f))
            .border(BorderStroke(2.dp, Color.DarkGray))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_1),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "0..20",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_2),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "20..30",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_3),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "30..45",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_4),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "45..60",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_5),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "60+",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(percentHeight(.1f))
                .background(color = MaterialTheme.myColors.chart_6),
            contentAlignment = Alignment.Center,
        ){
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Above avg",
                style = MaterialTheme.typography.AgeGroup,
            )
        }
    }
}