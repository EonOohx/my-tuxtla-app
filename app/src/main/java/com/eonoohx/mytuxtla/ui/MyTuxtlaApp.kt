package com.eonoohx.mytuxtla.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eonoohx.mytuxtla.R

private enum class MyTuxtlaScreen {
    MainScreen,
    PlacesScreen,
    PlaceInfoScreen
}

@Composable
fun MyTuxtlaApp() {
    val viewModel: PlacesViewModel = viewModel()
    val currentUiState = viewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: MyTuxtlaScreen.MainScreen.name
    val context = LocalContext.current

    Scaffold(topBar = {
        MyTuxtlaTopAppBar(
            currentScreen = currentScreen,
            canNavigateBack = currentScreen != MyTuxtlaScreen.MainScreen.name,
            navigateUp = {
                navController.navigateUp()
            },
            title = currentUiState.value.currentCategory
        )
    }) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = MyTuxtlaScreen.MainScreen.name,
            modifier = Modifier.padding(innerPadding),
        ) {
            composable(route = MyTuxtlaScreen.MainScreen.name) {
                val categories = currentUiState.value.categories
                MainScreen(
                    gridItems = categories,
                    modifier = Modifier.fillMaxSize()
                ) {
                    viewModel.setPlacesByCategory(context.resources.getString(it.name))
                    navController.navigate(route = MyTuxtlaScreen.PlacesScreen.name)
                }
            }
            composable(route = MyTuxtlaScreen.PlacesScreen.name) {
                val places = currentUiState.value.places
                MainScreen(gridItems = places, modifier = Modifier.fillMaxSize()) { place ->
                    viewModel.updateCurrentPlace(place)
                    navController.navigate(route = MyTuxtlaScreen.PlaceInfoScreen.name)
                }
            }
            composable(route = MyTuxtlaScreen.PlaceInfoScreen.name) {

                PlaceInfoScreen(
                    place = currentUiState.value.currentPlace,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTuxtlaTopAppBar(
    currentScreen: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        title = {
            val titleText =
                if (currentScreen != MyTuxtlaScreen.MainScreen.name) title
                else stringResource(R.string.app_name)

            Text(
                text = titleText,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.up_button)
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyTuxtlaAppPreview() {
    MyTuxtlaApp()
}