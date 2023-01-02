package com.example.mementomori.screen.modifier

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.example.mementomori.ui.theme.myColors

object CustomModifier{
    @Composable
    fun colorsOfTextField() = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.myColors.none,
        //disabledTextColor = disabledTextColor,
        backgroundColor = MaterialTheme.myColors.bg_card,
        cursorColor = MaterialTheme.myColors.focusLine,
        focusedIndicatorColor = MaterialTheme.myColors.focusLine,
        focusedLabelColor = MaterialTheme.myColors.none,
        unfocusedLabelColor = MaterialTheme.myColors.none,
        unfocusedIndicatorColor = MaterialTheme.myColors.none,
        trailingIconColor = MaterialTheme.myColors.txtIconBtn,
        //errorCursorColor = errorCursorColor,
    )

    @Composable
    fun colorsOfDropDown() = TextFieldDefaults.textFieldColors(
        textColor = MaterialTheme.myColors.none,
        //disabledTextColor = disabledTextColor,
        backgroundColor = MaterialTheme.myColors.main_300,
        cursorColor = MaterialTheme.myColors.focusLine,
        focusedIndicatorColor = MaterialTheme.myColors.focusLine,
        focusedLabelColor = MaterialTheme.myColors.none,
        unfocusedLabelColor = MaterialTheme.myColors.none,
        unfocusedIndicatorColor = MaterialTheme.myColors.none,
        trailingIconColor = MaterialTheme.myColors.txtIconBtn,
        //errorCursorColor = errorCursorColor,
    )
}
