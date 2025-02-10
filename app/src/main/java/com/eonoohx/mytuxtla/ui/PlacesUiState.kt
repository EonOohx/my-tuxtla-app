package com.eonoohx.mytuxtla.ui

import com.eonoohx.mytuxtla.model.Category
import com.eonoohx.mytuxtla.model.Place

data class PlacesUiState(
    val currentPlace: Place? = null,
    val currentCategory: String = "",
    val places: List<Place> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isOnListScreen: Boolean = true
)