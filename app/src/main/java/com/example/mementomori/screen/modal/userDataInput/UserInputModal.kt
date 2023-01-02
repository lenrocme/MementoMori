package com.example.mementomori.screen.modal.userDataInput

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mementomori.MainViewModel
import com.example.mementomori.R
import com.example.mementomori.helper.percentHeight
import com.example.mementomori.helper.percentWidth
import com.example.mementomori.screen.modifier.CustomModifier
import com.example.mementomori.ui.theme.Checkbox
import com.example.mementomori.ui.theme.Typography
import com.example.mementomori.ui.theme.UserInput
import com.example.mementomori.ui.theme.myColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalUserDataInput(mainVm: MainViewModel) {
    val focusManager = LocalFocusManager.current
    var expandMonthsDd by remember { mutableStateOf(false) }
    var expandYearDd by remember { mutableStateOf(false) }
    var expandCountryDd by remember { mutableStateOf(false) }
    //var checkedState by remember { mutableStateOf(true) }
    val dropDownMonths = mainVm.dataPick.months
    val dropDownYears = mainVm.dataPick.years
    val dropDownCountries = mainVm.dataPick.countryDict.keys

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.myColors.bg_modal.copy(alpha = .7f))
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                mainVm.userInputVm.isModalVisible = !mainVm.userInputVm.isModalVisible
            },
        contentAlignment = Alignment.Center,
        content = {
            Surface(
                shadowElevation = 5.dp,
                tonalElevation = 5.dp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = percentWidth(.06f), vertical = percentHeight(.06f))
                    .background(color = MaterialTheme.myColors.bg_card)
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        focusManager.clearFocus()
                    },
            ) {
                Column() {
                    Column(
                        modifier = Modifier
                            //.fillMaxHeight()
                            .height(percentHeight(1.0f - .12f * 2))
                            .verticalScroll(rememberScrollState())
                            .padding(horizontal = percentWidth(.06f))
                            .background(color = Color.Blue),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Red),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(percentWidth(.35f))
                            ) {
                                ExposedDropdownMenuBox(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.myColors.main_300),
                                    expanded = expandMonthsDd,
                                    onExpandedChange = { expandMonthsDd = !expandMonthsDd }
                                ) {
                                    OutlinedTextField(
                                        value = mainVm.userInputVm.bornMonth,
                                        onValueChange = {

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        readOnly = true,
                                        textStyle = Typography.UserInput,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandMonthsDd
                                            )
                                        },
                                        colors = CustomModifier.colorsOfDropDown(),
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expandMonthsDd,
                                        onDismissRequest = {
                                            expandMonthsDd = false
                                        },
                                    ) {
                                        dropDownMonths.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    mainVm.userInputVm.bornMonth = selectionOption
                                                    expandMonthsDd = false
                                                }
                                            ) {
                                                Text(text = selectionOption)
                                            }
                                        }
                                    }
                                }
                                Text(text = "Month")
                            }
                            Spacer(
                                modifier = Modifier
                                    .width(percentWidth(.06f))
                                    .background(color = Color.Blue)
                            )
                            Column(
                                modifier = Modifier
                                    .width(percentWidth(.35f))
                            ) {
                                ExposedDropdownMenuBox(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.myColors.main_300),
                                    expanded = expandYearDd,
                                    onExpandedChange = { expandYearDd = !expandYearDd }
                                ) {
                                    OutlinedTextField(
                                        value = mainVm.userInputVm.bornYear,
                                        onValueChange = {

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        readOnly = true,
                                        textStyle = Typography.UserInput,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandYearDd
                                            )
                                        },
                                        colors = CustomModifier.colorsOfDropDown(),
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expandYearDd,
                                        onDismissRequest = {
                                            expandYearDd = false
                                        },
                                    ) {
                                        dropDownYears.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    mainVm.userInputVm.bornYear = selectionOption
                                                    expandYearDd = false
                                                }
                                            ) {
                                                Text(text = selectionOption)
                                            }
                                        }
                                    }
                                }
                                Text(text = "Year")
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Red),
                            //horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(percentWidth(.76f))
                                    .background(color = Color.Yellow)
                            ) {
                                ExposedDropdownMenuBox(
                                    modifier = Modifier
                                        .background(color = MaterialTheme.myColors.main_300),
                                    expanded = expandCountryDd,
                                    onExpandedChange = { expandCountryDd = !expandCountryDd }
                                ) {
                                    OutlinedTextField(
                                        value = mainVm.userInputVm.country,
                                        onValueChange = {

                                        },
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .wrapContentHeight(),
                                        readOnly = true,
                                        textStyle = Typography.UserInput,
                                        trailingIcon = {
                                            ExposedDropdownMenuDefaults.TrailingIcon(
                                                expanded = expandCountryDd
                                            )
                                        },
                                        colors = CustomModifier.colorsOfDropDown(),
                                    )
                                    ExposedDropdownMenu(
                                        expanded = expandCountryDd,
                                        onDismissRequest = {
                                            expandCountryDd = false
                                        },
                                    ) {
                                        dropDownCountries.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    mainVm.userInputVm.country = selectionOption
                                                    expandCountryDd = false
                                                }
                                            ) {
                                                Text(text = selectionOption)
                                            }
                                        }
                                    }
                                }
                                Text(text = "Country of birth")
                            }
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Green),
                            //horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = mainVm.userInputVm.isSmoker,
                                onCheckedChange = {
                                    mainVm.userInputVm.isSmoker = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.myColors.checkedCheckbox,
                                    uncheckedColor = MaterialTheme.myColors.unCheckedCheckbox
                                )
                            )
                            Text(
                                text = "I'm smoker",
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    )
                                    {
                                        mainVm.userInputVm.isSmoker = !mainVm.userInputVm.isSmoker
                                    },
                                style = MaterialTheme.typography.Checkbox,
                                color = if (mainVm.userInputVm.isSmoker)
                                    MaterialTheme.myColors.fontUnCheckedCheckbox
                                else
                                    MaterialTheme.myColors.fontCheckedCheckbox
                            )

                        }
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxHeight()
                            .fillMaxWidth()
                            .padding(horizontal = percentWidth(.06f))
                            .background(color = Color.DarkGray),
                        verticalAlignment = Alignment.CenterVertically
                        ){
                        Icon(
                            modifier = Modifier
                                .size(percentWidth(.08f)),
                            painter = painterResource(id = R.drawable.ic_content_copy),
                            contentDescription = "copy to clipboard",
                            tint = MaterialTheme.myColors.main_800
                        )
                        Spacer(modifier = Modifier.width(percentWidth(.03f)))
                        Text(text = "By clicking this link, the link to the application on the google play will be stored in your clipboard")
                    }
                }
            }
        }
    )
}