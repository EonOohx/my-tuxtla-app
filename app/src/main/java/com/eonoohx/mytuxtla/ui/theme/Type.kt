package com.eonoohx.mytuxtla.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.eonoohx.mytuxtla.R

val daysone_regular = FontFamily(Font(resId = R.font.daysone_regular))
val andika_bold = FontFamily(Font(resId = R.font.andika_bold))

val baseline = Typography()
// Default Material 3 typography values
// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = baseline.displayLarge.copy(fontFamily = daysone_regular),
    displaySmall = baseline.displaySmall.copy(fontFamily = daysone_regular),
    titleLarge = baseline.titleLarge.copy(fontFamily = andika_bold),
    titleMedium = baseline.titleMedium.copy(fontFamily = andika_bold),
    titleSmall = baseline.titleSmall.copy(fontFamily = andika_bold),
    bodyLarge = baseline.bodyLarge.copy(fontFamily = andika_bold),
    bodyMedium = baseline.bodyMedium.copy(fontFamily = andika_bold),
    bodySmall = baseline.bodySmall.copy(fontFamily = andika_bold),
    labelLarge = baseline.labelLarge.copy(fontFamily = andika_bold),
    labelMedium = baseline.labelMedium.copy(fontFamily = andika_bold),
    labelSmall = baseline.labelSmall.copy(fontFamily = andika_bold),
)
