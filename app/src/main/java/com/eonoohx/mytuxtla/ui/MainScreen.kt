package com.eonoohx.mytuxtla.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.data.PlaceCategoryType
import com.eonoohx.mytuxtla.data.local.PlacesStoreDataProvider
import com.eonoohx.mytuxtla.model.MenuItem
import com.eonoohx.mytuxtla.model.Place
import com.eonoohx.mytuxtla.ui.theme.MyTuxtlaTheme
import com.eonoohx.mytuxtla.ui.theme.cardShape

@Composable
fun MainScreen(
    isOnExtendedCategoryScreen: Boolean,
    gridItems: List<MenuItem>,
    modifier: Modifier = Modifier,
    onCategoryPressed: (MenuItem) -> Unit,
) {
    Surface(modifier = modifier, color = MaterialTheme.colorScheme.surface) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small))
        ) {
            items(gridItems) { item ->
                Card(
                    modifier = modifier
                        .wrapContentHeight()
                        .padding(dimensionResource(R.dimen.padding_small)),
                    onClick = {
                        onCategoryPressed(item)
                    },
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.inversePrimary),
                    shape = cardShape
                ) {
                    Image(
                        painter = painterResource(item.image),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = if (!isOnExtendedCategoryScreen) Modifier
                            .height(120.dp)
                            .fillMaxWidth()
                        else Modifier
                            .height(280.dp)
                            .fillMaxWidth()
                    )
                    Row(modifier = Modifier.height(80.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = stringResource(item.name),
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth().padding(dimensionResource(R.dimen.padding_small))
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreenAndDetails(
    gridItems: List<MenuItem>,
    place: Place?,
    modifier: Modifier = Modifier,
    onCategoryPressed: (MenuItem) -> Unit,
) {
    Row(modifier = modifier) {
        MainScreen(
            isOnExtendedCategoryScreen = false,
            gridItems = gridItems,
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        ) { onCategoryPressed(it) }
        PlaceInfoScreen(
            place = place, modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    MyTuxtlaTheme {
        MainScreen(
            isOnExtendedCategoryScreen = false,
            gridItems = PlacesStoreDataProvider.listOfCategories,
            modifier = Modifier.fillMaxSize()
        ) {}
    }
}

@Preview(showBackground = true)
@Composable
fun PlacesScreenPreview() {
    MyTuxtlaTheme {
        MainScreen(
            isOnExtendedCategoryScreen = false,
            gridItems = PlacesStoreDataProvider.listOfPlaces.filter
            { it.category == PlaceCategoryType.SHOPPING_PLACES.name },
            modifier = Modifier.fillMaxSize()
        ) {}
    }
}

@Preview(showBackground = true, widthDp = 1000, showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun MainScreenAndDetailsPreview() {
    MyTuxtlaTheme {
        MainScreenAndDetails(
            gridItems = PlacesStoreDataProvider.listOfPlaces.filter
            { place -> place.category == PlaceCategoryType.RESTAURANTS.name },
            place = PlacesStoreDataProvider.listOfPlaces[0]
        ) { }
    }
}

@Preview(showBackground = true, widthDp = 1000, device = Devices.PIXEL_TABLET)
@Composable
fun MainScreenExpandedPreview() {
    MyTuxtlaTheme {
        MyTuxtlaTheme {
            MainScreen(
                isOnExtendedCategoryScreen = true,
                gridItems = PlacesStoreDataProvider.listOfCategories,
                modifier = Modifier.fillMaxSize()
            ) {}
        }
    }
}