package com.eonoohx.mytuxtla.ui


import androidx.lifecycle.ViewModel
import com.eonoohx.mytuxtla.data.local.PlacesStoreDataProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class PlacesViewModel : ViewModel() {
    private val _uiState =
        MutableStateFlow(
            PlacesUiState(
                categories = PlacesStoreDataProvider.listOfCategories
            )
        )
    val uiState: StateFlow<PlacesUiState> = _uiState.asStateFlow()

    fun setPlacesByCategory(category: String) {
        _uiState.update { currentState ->
            val categoryPlace = category.uppercase()
                .replace(
                    " ",
                    "_"
                )
            currentState.copy(
                places = PlacesStoreDataProvider.listOfPlaces.groupBy { it.category
                }[categoryPlace]!!
            )
        }
    }

}