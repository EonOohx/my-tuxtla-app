package com.eonoohx.mytuxtla.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.eonoohx.mytuxtla.data.CategoryPlaceType

data class Category(
    val name: String,
    @StringRes val info: Int,
    @DrawableRes val image: Int
)