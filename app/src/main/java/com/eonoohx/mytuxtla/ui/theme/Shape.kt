package com.eonoohx.mytuxtla.ui.theme

import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val shapes = Shapes(
    medium = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
)

val cardShape = shapes.medium.copy(bottomStart = CornerSize(16.dp), bottomEnd = CornerSize(16.dp))
