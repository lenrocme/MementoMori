package com.example.mementomori.screen.modifier

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import com.example.mementomori.ui.theme.myColors

object CustomModifier{
    @Composable
    fun colorsOfTextField() = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.myColors.none,
        //disabledTextColor = disabledTextColor,
        backgroundColor = MaterialTheme.myColors.bg_card,
        cursorColor = MaterialTheme.myColors.focusLine,
        focusedLabelColor = MaterialTheme.myColors.none,
        unfocusedLabelColor = MaterialTheme.myColors.none,
        trailingIconColor = MaterialTheme.myColors.txtIconBtn,
        //errorCursorColor = errorCursorColor,
        /*textColor = MaterialTheme.myColors.main_400,
                            unfocusedBorderColor = MaterialTheme.myColors.main_350,
                            focusedBorderColor = MaterialTheme.myColors.main_400,
                            unfocusedLabelColor = MaterialTheme.myColors.main_400,
                            focusedLabelColor = MaterialTheme.myColors.main_400,
                            disabledTextColor = Color.Transparent,
                            backgroundColor = MaterialTheme.myColors.CL_BackGround,
                            cursorColor = MaterialTheme.myColors.main_400,*/
    )

    @Composable
    fun colorsOfDropDown() = TextFieldDefaults.outlinedTextFieldColors(
        textColor = MaterialTheme.myColors.none,
        //disabledTextColor = disabledTextColor,
        backgroundColor = MaterialTheme.myColors.main_300,
        cursorColor = MaterialTheme.myColors.focusLine,
        //focusedIndicatorColor = MaterialTheme.myColors.focusLine,
        focusedLabelColor = MaterialTheme.myColors.none,
        unfocusedLabelColor = MaterialTheme.myColors.none,
       // unfocusedIndicatorColor = MaterialTheme.myColors.none,
        trailingIconColor = MaterialTheme.myColors.main_000,
        //errorCursorColor = errorCursorColor,
    )
}
