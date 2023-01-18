package com.malferma.mementomori.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.graphics.Color

val LocalColors = staticCompositionLocalOf { LightColorScheme }

private val DarkColorScheme = CustomColors(
    none = none,
    colorScheme = lightColors(),

    main_fff = main_fff,
    main_100 = main_100,
    main_200 = main_200,
    main_300 = main_300,
    main_400 = main_400,
    main_500 = main_500,
    main_600 = main_600,
    main_700 = main_700,
    main_800 = main_800,
    main_900 = main_900,
    main_000 = main_000,

    /** Custom */
    chart_1 = chart_1,
    chart_2 = chart_2,
    chart_3 = chart_3,
    chart_4 = chart_4,
    chart_5 = chart_5,
    chart_6 = chart_6,
    chart_x = chart_x,
    chart_bg_above = chart_bg_above,
    chart_above = chart_above,

    focusLine = none,
    iconButton = none,
    txtIconBtn = none,
    /** Checkbox */
    checkedCheckbox = none,
    unCheckedCheckbox = none,

    /** Switch */
    switchThumb = none,
    switchTrack = none,

    bgTaxClass = none,
    bgTaxClassSelect = none,
    bg_card = main_700,
    bg_modal = modalTransBground,
    bgHeader = bgHeader,
    headerItems = headerItems,
    /**
     * Font colors
     * */
    fontTaxButton = none,
    fontHeader = none,
    fontLabel = none,
    fontLabelCard = none,
    fontCheckedCheckbox = none,
    fontUnCheckedCheckbox = none,
    fontModal = main_300,
)

private val LightColorScheme = CustomColors(
    none = none,
    colorScheme = lightColors(),

    main_fff = main_fff,
    main_100 = main_100,
    main_200 = main_200,
    main_300 = main_300,
    main_400 = main_400,
    main_500 = main_500,
    main_600 = main_600,
    main_700 = main_700,
    main_800 = main_800,
    main_900 = main_900,
    main_000 = main_000,

    /** Custom */
    chart_1 = chart_1,
    chart_2 = chart_2,
    chart_3 = chart_3,
    chart_4 = chart_4,
    chart_5 = chart_5,
    chart_6 = chart_6,
    chart_x = chart_x,
    chart_bg_above = chart_bg_above,
    chart_above = chart_above,

    focusLine = none,
    iconButton = none,
    txtIconBtn = none,
    /** Checkbox */
    checkedCheckbox = none,
    unCheckedCheckbox = none,

    /** Switch */
    switchThumb = none,
    switchTrack = none,

    bgTaxClass = none,
    bgTaxClassSelect = none,
    bg_card = main_200,
    bg_modal = modalTransBground,
    bgHeader = bgHeader,
    headerItems = headerItems,
    /**
     * Font colors
     * */
    fontTaxButton = none,
    fontHeader = none,
    fontLabel = none,
    fontLabelCard = none,
    fontCheckedCheckbox = none,
    fontUnCheckedCheckbox = none,
    fontModal = bgHeader,
)

@Composable
fun CustomMaterialTheme(theme: String = "Default", content: @Composable () -> Unit) {
    var darkTheme = when (theme) {
        "Light" -> false
        "Dark" -> true
        else -> isSystemInDarkTheme()
    }
    if (theme == "Default")
        darkTheme = isSystemInDarkTheme()
    val colors = if (darkTheme) {
        DarkColorScheme
    } else {
        LightColorScheme
    }

    CompositionLocalProvider(LocalColors provides colors) {
        MaterialTheme(
            colors = colors.colorScheme,
            typography = Typography,
            content = content
        )
    }
}