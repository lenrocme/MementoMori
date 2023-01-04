package com.example.mementomori.ui.theme

import androidx.compose.material.Colors
import androidx.compose.ui.graphics.Color

data class CustomColors(
    val none: Color,
    val colorScheme: Colors,

    val main_fff: Color,
    val main_100: Color,
    val main_200: Color,
    val main_300: Color,
    val main_400: Color,
    val main_500: Color,
    val main_600: Color,
    val main_700: Color,
    val main_800: Color,
    val main_900: Color,
    val main_000: Color,

    /** Custom */
    val chart_1: Color,
    val chart_2: Color,
    val chart_3: Color,
    val chart_4: Color,
    val chart_5: Color,
    val chart_6: Color,
    val chart_x: Color,
    val chart_bg_above: Color,
    val chart_above: Color,

    val focusLine: Color,
    val iconButton: Color,
    val txtIconBtn: Color,
    /** Checkbox */
    val checkedCheckbox: Color,
    val unCheckedCheckbox: Color,

    /** Switch */
    val switchThumb: Color,
    val switchTrack: Color,

    val bgTaxClass: Color,
    val bgTaxClassSelect: Color,
    val bg_card: Color,
    val bg_modal: Color,

    /**
     * Font colors
     * */
    val fontTaxButton: Color,
    val fontHeader: Color,
    val fontLabel: Color,
    val fontLabelCard: Color,
    val fontCheckedCheckbox: Color,
    val fontUnCheckedCheckbox: Color,
) {
    val primary: Color get() = colorScheme.primary
    val primaryVariant: Color get() = colorScheme.primaryVariant
    val secondary: Color get() = colorScheme.secondary
    val secondaryVariant: Color get() = colorScheme.secondaryVariant
    val background: Color get() = colorScheme.background
    val surface: Color get() = colorScheme.surface
    val error: Color get() = colorScheme.error
    val onPrimary: Color get() = colorScheme.onPrimary
    val onSecondary: Color get() = colorScheme.onSecondary
    val onBackground: Color get() = colorScheme.onBackground
    val onSurface: Color get() = colorScheme.onSurface
    val onError: Color get() = colorScheme.onError
    val isLight: Boolean get() = colorScheme.isLight
}