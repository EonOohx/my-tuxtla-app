package com.eonoohx.mytuxtla.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.model.MenuItem
import com.eonoohx.mytuxtla.ui.components.ResizableText

private enum class MyTuxtlaScreen {
    MainScreen,
    PlacesScreen,
    PlaceInfoScreen
}

private fun numOfRowsByCategories(numCategories: Int): Int {
    val mod = numCategories.mod(2)
    val numRows = numCategories.div(2)
    return if (mod == 0) numRows else numRows + 1
}

@Composable
fun MyTuxtlaApp() {
    val viewModel: PlacesViewModel = viewModel()
    val currentUiState = viewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()

    Scaffold(topBar = { MyTuxtlaTopAppBar() }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyTuxtlaScreen.MainScreen.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = MyTuxtlaScreen.MainScreen.name) {
                val categories = currentUiState.value.categories
                GridScreen(
                    gridItems = categories,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Log.i("CATEGORY", it)
                    viewModel.setPlacesByCategory(it)
                    navController.navigate(route = MyTuxtlaScreen.PlacesScreen.name)
                }
            }
            composable(route = MyTuxtlaScreen.PlacesScreen.name) {
                val places = currentUiState.value.places
                GridScreen(gridItems = places, modifier = Modifier.fillMaxSize()) {
                    navController.navigate(route = MyTuxtlaScreen.PlaceInfoScreen.name)
                }
            }
            composable(route = MyTuxtlaScreen.PlaceInfoScreen.name) {
                PlaceInfoScreen(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyTuxtlaTopAppBar(modifier: Modifier = Modifier) {

}

@Composable
fun GridScreen(
    gridItems: List<MenuItem>,
    modifier: Modifier = Modifier,
    onCategoryPressed: (String) -> Unit,
) {
    val context = LocalContext.current
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(dimensionResource(R.dimen.padding_small)),
        modifier = modifier
    ) {
        items(gridItems) { item ->
            Card(
                modifier = modifier
                    .fillMaxHeight()
                    .padding(dimensionResource(R.dimen.padding_small)),
                onClick = { onCategoryPressed(context.resources.getString(item.name)) }
            ) {
                Image(
                    painter = painterResource(item.image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.aspectRatio(1f)
                )
                ResizableText(
                    text = stringResource(item.name), modifier = Modifier.padding(
                        dimensionResource(R.dimen.padding_small)
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MyTuxtlaAppPreview() {
    MyTuxtlaApp()
}