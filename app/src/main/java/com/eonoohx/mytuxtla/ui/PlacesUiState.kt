package com.eonoohx.mytuxtla.ui

import com.eonoohx.mytuxtla.data.local.PlacesStoreDataProvider
import com.eonoohx.mytuxtla.model.MenuItem
import com.eonoohx.mytuxtla.model.Place

data class PlacesUiState(
    val currentPlace: Place? = null,
    val currentCategory: String = "",
    val places: List<Place> = emptyList(),
    val categories: List<MenuItem> = emptyList(),
)