package com.malferma.mementomori.screen.modal.userDataInput

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.malferma.mementomori.MainViewModel
import com.malferma.mementomori.data.lastInput.LastInputViewModel
import com.malferma.mementomori.helper.percentHeight
import com.malferma.mementomori.helper.percentWidth
import com.malferma.mementomori.screen.modifier.CustomModifier
import com.malferma.mementomori.ui.theme.Checkbox
import com.malferma.mementomori.ui.theme.Typography
import com.malferma.mementomori.ui.theme.UserInput
import com.malferma.mementomori.ui.theme.myColors

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ModalUserDataInput(mainVm: MainViewModel, mUserForm: LastInputViewModel) {
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
            .padding(top = 45.dp)
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
                    .wrapContentHeight()
                    .padding(horizontal = percentWidth(.07f), vertical = percentHeight(.07f))
                    .clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        focusManager.clearFocus()
                    },
                shape = RoundedCornerShape(5),
            ) {
                //Column() {
                    Column(
                        modifier = Modifier
                            //.height(percentHeight(1.0f - .12f * 2))
                            .verticalScroll(rememberScrollState())
                            .background(color = MaterialTheme.myColors.bg_card)
                            .padding(top = percentWidth(.11f), bottom = percentWidth(.06f))
                            .padding(horizontal = percentWidth(.06f)),
                        verticalArrangement = Arrangement.Center,
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                        ) {
                            /** Country picker */
                            Column(
                                modifier = Modifier
                                    .width(percentWidth(.76f))
                            ) {
                                ExposedDropdownMenuBox(
                                    modifier = Modifier,
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
                                        modifier = Modifier
                                            .background(color = MaterialTheme.myColors.bg_card_above),
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
                                                Text(
                                                    text = selectionOption,
                                                    color = MaterialTheme.myColors.fontModal,
                                                )
                                            }
                                        }
                                    }
                                }
                                Text(
                                    text = "Country of birth",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.myColors.fontModal)
                            }
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Column(
                                modifier = Modifier
                                    .width(percentWidth(.35f))
                            ) {
                                ExposedDropdownMenuBox(
                                    modifier = Modifier,
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
                                        modifier = Modifier
                                            .background(color = MaterialTheme.myColors.bg_card_above),
                                    ) {
                                        dropDownMonths.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    _bornMonth = selectionOption
                                                    expandMonthsDd = false
                                                }
                                            ) {
                                                Text(
                                                    text = selectionOption,
                                                    color = MaterialTheme.myColors.fontModal,
                                                )
                                            }
                                        }
                                    }
                                }
                                Text(text = "Month",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.myColors.fontModal)
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
                                    modifier = Modifier,
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
                                        modifier = Modifier
                                            .background(color = MaterialTheme.myColors.bg_card_above),
                                    ) {
                                        dropDownYears.forEach { selectionOption ->
                                            DropdownMenuItem(
                                                onClick = {
                                                    _bornYear = selectionOption
                                                    expandYearDd = false
                                                }
                                            ) {
                                                Text(
                                                    text = selectionOption,
                                                    color = MaterialTheme.myColors.fontModal,
                                                )
                                            }
                                        }
                                    }
                                }
                                Text(text = "Year",
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Right,
                                    color = MaterialTheme.myColors.fontModal)
                            }
                        }
                        Spacer(modifier = Modifier.height(5.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
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
                                        },
                                        /*colors = RadioButtonColors.radioColor(
                                            enabled = MaterialTheme.myColors.bgHeader,
                                            selected = MaterialTheme.myColors.bgHeader
                                        )*/
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
                                .fillMaxWidth(),
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
                                    uncheckedColor = MaterialTheme.myColors.unCheckedCheckbox,
                                    checkmarkColor = MaterialTheme.myColors.bg_card,
                                )
                            )
                            Text(
                                text = "Yes, I am a smoker",
                                modifier = Modifier
                                    .clickable(
                                        interactionSource = MutableInteractionSource(),
                                        indication = null
                                    )
                                    {
                                        _isSmoker = !_isSmoker
                                    },
                                style = MaterialTheme.typography.Checkbox,
                                color = if (_isSmoker)
                                    MaterialTheme.myColors.fontCheckedCheckbox
                                else
                                    MaterialTheme.myColors.fontUnCheckedCheckbox
                            )

                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center,
                        ) {
                            Button(
                                onClick = {
                                    mainVm.userInputVm.setData(_country, _bornMonth, _bornYear, _isMale, _isSmoker)
                                    mainVm.userDataVM.calc(mainVm.userInputVm)
                                    mainVm.userInputVm.isModalVisible = false
                                    val lastInput = mainVm.userInputVm.getLastInput()
                                    mUserForm.update(lastInput) // save to the db
                                },
                                elevation =  ButtonDefaults.elevation(
                                    defaultElevation = 10.dp,
                                    pressedElevation = 15.dp,
                                    disabledElevation = 0.dp,
                                ),
                                colors = ButtonDefaults.buttonColors(
                                    backgroundColor = MaterialTheme.myColors.bgButtonColor,
                                    contentColor = MaterialTheme.myColors.contentButtonColor),
                                content = {
                                    Text(text = "Save")
                                })
                        }
                    }
                    /*Row(
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
                    }*/
               // }
            }
        }
    )
}