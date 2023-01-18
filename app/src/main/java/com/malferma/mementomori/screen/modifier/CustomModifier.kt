package com.malferma.mementomori.screen.modifier

import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.malferma.mementomori.ui.theme.myColors

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
        textColor = MaterialTheme.myColors.fontModal,
        //disabledTextColor = disabledTextColor,
        backgroundColor = Color.Transparent,
        cursorColor = MaterialTheme.myColors.focusLine,
        focusedBorderColor = MaterialTheme.myColors.focusLine,
        unfocusedBorderColor = MaterialTheme.myColors.focusLine,
        //focusedIndicatorColor = MaterialTheme.myColors.focusLine,
        focusedLabelColor = MaterialTheme.myColors.none,
        unfocusedLabelColor = MaterialTheme.myColors.none,
       // unfocusedIndicatorColor = MaterialTheme.myColors.none,
        trailingIconColor = MaterialTheme.myColors.focusLine,
        //errorCursorColor = errorCursorColor,
    )
}
