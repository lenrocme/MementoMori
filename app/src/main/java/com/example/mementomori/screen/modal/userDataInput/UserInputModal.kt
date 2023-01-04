package com.example.mementomori.screen.modal.userDataInput

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CutCornerShape
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
    val dropDownMonths = mainVm.dataPick.months
    val dropDownYears = mainVm.dataPick.years
    val dropDownCountries = mainVm.dataPick.countryDict.keys

    /** user imputed data */
    var _bornYear: String by remember {mutableStateOf(mainVm.userInputVm.bornYear)}
    var _bornMonth: String by remember {mutableStateOf(mainVm.userInputVm.bornMonth)}
    var _country: String by remember {mutableStateOf(mainVm.userInputVm.country)}
    var _isSmoker: Boolean by remember {mutableStateOf(mainVm.userInputVm.isSmoker)}
    var _isMale: Boolean by remember {mutableStateOf(mainVm.userInputVm.isMale)}

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.myColors.bg_modal.copy(alpha = .7f))
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                mainVm.userInputVm.isModalVisible = false
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
                                        //value = mainVm.userInputVm.bornMonth,
                                        value = _bornMonth,
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
                                                    _bornMonth = selectionOption
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
                                        value = _bornYear,
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
                                                    _bornYear = selectionOption
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
                                .background(color = Color.Green),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            /** Sex picker */
                            Row(
                                modifier = Modifier
                                    .width(percentWidth(.38f)),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    RadioButton(
                                        selected = _isMale,
                                        onClick = {
                                            _isMale = true
                                        }
                                    )
                                    Text(
                                        text = "Male",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable(
                                                interactionSource = MutableInteractionSource(),
                                                indication = null
                                            ) {
                                                _isMale = true
                                            }
                                    )
                                }
                            )
                            Row(
                                modifier = Modifier
                                    .width(percentWidth(.38f)),
                                verticalAlignment = Alignment.CenterVertically,
                                content = {
                                    RadioButton(
                                        selected = !_isMale,
                                        onClick = {
                                            _isMale = false
                                        }
                                    )
                                    Text(
                                        text = "Female",
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable(
                                                interactionSource = MutableInteractionSource(),
                                                indication = null
                                            ) {
                                                _isMale = false
                                            }
                                    )
                                }
                            )
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Red),
                        ) {
                            /** Country picker */
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
                                        value = _country,
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
                                                    _country = selectionOption
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
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            /** Agreement about smoking checkbox */
                            Checkbox(
                                checked = _isSmoker,
                                onCheckedChange = {
                                    _isSmoker = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkedColor = MaterialTheme.myColors.checkedCheckbox,
                                    uncheckedColor = MaterialTheme.myColors.unCheckedCheckbox
                                )
                            )
                            Text(
                                text = "Yes, I am a smoker",
                                modifier = Modifier
                                    .background(color = Color.White)
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    )
                                    {
                                        _isSmoker = !_isSmoker
                                    },
                                style = MaterialTheme.typography.Checkbox,
                                color = if (_isSmoker)
                                    MaterialTheme.myColors.fontUnCheckedCheckbox
                                else
                                    MaterialTheme.myColors.fontCheckedCheckbox
                            )

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = Color.Green),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(
                                onClick = {
                                    mainVm.userInputVm.setData(_country, _bornMonth, _bornYear, _isMale, _isSmoker)
                                    mainVm.userDataVM.calc(mainVm.userInputVm)
                                    mainVm.userInputVm.isModalVisible = false
                                },
                                elevation =  ButtonDefaults.elevation(
                                    defaultElevation = 10.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                ),
                                content = {
                                    Text(text = "Save")
                                })
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