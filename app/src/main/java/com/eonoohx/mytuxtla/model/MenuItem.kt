package com.eonoohx.mytuxtla.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface MenuItem {
    @get: StringRes
    val name: Int

    @get: DrawableRes
    val image: Int
}

data class PlaceCategory(
    @StringRes override val name: Int,
    override val image: Int
) : MenuItem

data class Place(
    @StringRes override val name: Int,
    @StringRes val info: Int,
    val category: String,
    @DrawableRes override val image: Int,
) : MenuItem