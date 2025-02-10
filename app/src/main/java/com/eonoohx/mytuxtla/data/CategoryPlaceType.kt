package com.eonoohx.mytuxtla.data

import androidx.annotation.StringRes
import com.eonoohx.mytuxtla.R

enum class CategoryPlaceType(@StringRes val categoryPlace: Int) {
    COFFEE_SHOPS(R.string.coffee_shops),
    RESTAURANTS(R.string.restaurants),
    FAMILY_PLACES(R.string.family_places),
    PARKS(R.string.parks),
    SHOPPING_PLACES(R.string.shopping_places)
}
