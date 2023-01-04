package com.example.mementomori.screen.header

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mementomori.MainViewModel
import com.example.mementomori.R
import com.example.mementomori.ui.theme.myColors

@Composable
fun HeaderView(mainVm: MainViewModel) {
    val focusManager = LocalFocusManager.current
   /* Box(
        modifier = Modifier
            .wrapContentSize(),
            //.background(color = MaterialTheme.colors.onSurface)
    ) {*/
        Surface(
            //shape = RectangleShape,
            modifier = Modifier
                .wrapContentSize()
                .shadow(elevation = 10.dp, shape = RectangleShape)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
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
                }
                .background(color = MaterialTheme.myColors.bgHeader),
            //.padding(bottom = getPaddingCards()),
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
                            .padding(5.dp)
                            .size(35.dp)
                            .rotate(90f),
                        painter = painterResource(id = R.drawable.ic_baseline_bar_chart_24),
                        contentDescription = "stats",
                        tint = MaterialTheme.myColors.main_300
                    )
                    Spacer(
                        modifier = Modifier
                            .weight(1f )
                    )
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .size(35.dp),
                        painter = painterResource(id = R.drawable.ic_outline_info_24),
                        contentDescription = "info",
                        tint = MaterialTheme.myColors.main_300
                    )
                }
                Column() {

                }
            }
        }

}