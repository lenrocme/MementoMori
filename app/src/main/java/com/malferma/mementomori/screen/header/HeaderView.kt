package com.malferma.mementomori.screen.header

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
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
import com.malferma.mementomori.MainViewModel
import com.malferma.mementomori.R
import com.malferma.mementomori.helper.percentWidth
import com.malferma.mementomori.ui.theme.*

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
                "Above life expectancy: ${mainVm.userDataVM.aboveAverageLifeExpect}\n${mainVm.userDataVM.aboveAverageLifeExpect} months(Leafs) lived above life expectancy",
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
            text = "Life expectancy: ${mainVm.userDataVM.userLifeExpectation} months(Leafs)",
            style = MaterialTheme.typography.HeaderContainer,
        )
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
private fun HeaderInfoChart(mainVm: MainViewModel) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState()),
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        LegendOfChart()
        Spacer(modifier = Modifier.height(30.dp))
        LegendOfAgeGroup()
        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
private fun LegendOfChart() {
    Column(
        modifier = Modifier
            .width(percentWidth(.75f)),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(percentWidth(1f) / 14),
                content = {
                    Icon(
                        modifier = Modifier,
                        contentDescription = "leaf",
                        painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                        tint = Color.Blue,
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Each leaf represents one month",
                style = MaterialTheme.typography.HeaderContainerInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(percentWidth(1f) / 14),
                content = {
                    Icon(
                        modifier = Modifier,
                        contentDescription = "leaf",
                        painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                        tint = Color.Red,
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Each red leaf represents 12th month of a year",
                style = MaterialTheme.typography.HeaderContainerInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(percentWidth(1f) / 14),
                content = {
                    Icon(
                        modifier = Modifier,
                        contentDescription = "leaf",
                        painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                        tint = MaterialTheme.myColors.chart_above,
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Represents a month lived beyond life expectancy",
                style = MaterialTheme.typography.HeaderContainerInfo,
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(percentWidth(1f) / 14),
                content = {
                    Icon(
                        modifier = Modifier,
                        contentDescription = "leaf",
                        painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                        tint = Color.Blue,
                    )

                    Icon(
                        Icons.Default.Clear,
                        modifier = Modifier
                            .size(percentWidth(1f) / 14),
                        contentDescription = "spent leaf",
                        tint = MaterialTheme.myColors.chart_x
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Represents a month spent",
                style = MaterialTheme.typography.HeaderContainerInfo,
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(percentWidth(1f) / 14)
                    //.border(BorderStroke(1.dp, MaterialTheme.myColors.chart_x)),
                    .background(color = Color.White),
                contentAlignment = Alignment.Center,
                content = {
                    Icon(
                        modifier = Modifier,
                        contentDescription = "leaf",
                        painter = painterResource(id = R.drawable.leaf_svgrepo_com),
                        tint = Color.Blue,
                    )
                }
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                modifier = Modifier,
                text = "Represent actual month(leaf)",
                style = MaterialTheme.typography.HeaderContainerInfo,
            )
        }
    }
}

@Composable
private fun LegendOfAgeGroup() {
    Column()
    {
        Text(
            modifier = Modifier
                .width(percentWidth(.75f)),
            text = "Age group",
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.myColors.headerItems,
        )
        Column(
            modifier = Modifier
                .width(percentWidth(.75f))
                .border(BorderStroke(2.dp, Color.DarkGray))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_1)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "0..20",
                        style = MaterialTheme.typography.AgeGroup,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "0..240",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_2)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "20..30",
                        style = MaterialTheme.typography.AgeGroup,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "240..360",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_3)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "30..45",
                        style = MaterialTheme.typography.AgeGroup,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "360..540",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_4)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "540..20",
                        style = MaterialTheme.typography.AgeGroup,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "540..720",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_5)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "60+ years",
                        style = MaterialTheme.typography.AgeGroup,
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "720+ months",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.myColors.chart_6)
                    .padding(vertical = 7.dp),
                contentAlignment = Alignment.Center,
            ) {
                Column() {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "Above avg of \nlife expectancy",
                        style = MaterialTheme.typography.AgeGroupMonths,
                    )
                }
            }
        }
    }
}