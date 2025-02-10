package com.eonoohx.mytuxtla.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Place(
    @StringRes val name: Int,
    @StringRes val info: Int,
    val category: String,
    @DrawableRes val image: Int
)