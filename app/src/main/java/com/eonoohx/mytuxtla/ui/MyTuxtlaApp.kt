package com.eonoohx.mytuxtla.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.ui.theme.MyTuxtlaTheme
import com.eonoohx.mytuxtla.ui.utils.WindowContentType

private enum class MyTuxtlaScreen {
    MainScreen,
    PlacesScreen,
    PlaceInfoScreen
}

@Composable
fun MyTuxtlaApp(windowSize: WindowWidthSizeClass, modifier: Modifier = Modifier) {
    val viewModel: PlacesViewModel = viewModel()
    val currentUiState = viewModel.uiState.collectAsState()
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = backStackEntry?.destination?.route ?: MyTuxtlaScreen.MainScreen.name
    val context = LocalContext.current

    val contentType = if (windowSize == WindowWidthSizeClass.Expanded)
        WindowContentType.LIST_AND_DETAILS else WindowContentType.LIST_ONLY

    Scaffold(modifier = modifier, topBar = {
        MyTuxtlaTopAppBar(
            currentScreen = currentScreen,
            canNavigateBack = currentScreen != MyTuxtlaScreen.MainScreen.name,
            navigateUp = {
                if (contentType == WindowContentType.LIST_AND_DETAILS) {
                    if (currentScreen != MyTuxtlaScreen.MainScreen.name)
                        viewModel.resetInfoPlaceScreen()
                }
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
                    isOnExtendedCategoryScreen = contentType == WindowContentType.LIST_AND_DETAILS,
                    gridItems = categories,
                    modifier = Modifier.fillMaxSize()
                ) {
                    viewModel.setPlacesByCategory(context.resources.getString(it.name))
                    navController.navigate(route = MyTuxtlaScreen.PlacesScreen.name)
                }
            }

            composable(route = MyTuxtlaScreen.PlacesScreen.name) {
                val places = currentUiState.value.places
                if (contentType == WindowContentType.LIST_ONLY) {
                    MainScreen(
                        isOnExtendedCategoryScreen = false,
                        gridItems = places,
                        modifier = Modifier.fillMaxSize()
                    ) { place ->
                        viewModel.updateCurrentPlace(place)
                        navController.navigate(route = MyTuxtlaScreen.PlaceInfoScreen.name)
                    }
                } else {
                    MainScreenAndDetails(
                        gridItems = places,
                        place = currentUiState.value.currentPlace
                    ) { place ->
                        viewModel.updateCurrentPlace(place)
                    }
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
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.displaySmall
            )
        },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.up_button),
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyTuxtlaAppPreview() {
    MyTuxtlaTheme {
        MyTuxtlaApp(windowSize = WindowWidthSizeClass.Compact)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MyTuxtlaAppDarkPreview() {
    MyTuxtlaTheme(darkTheme = true) {
        MyTuxtlaApp(windowSize = WindowWidthSizeClass.Compact)
    }
}

@Preview(showBackground = true, widthDp = 1000, showSystemUi = true, device = Devices.PIXEL_TABLET)
@Composable
fun MyTuxtlaAppExpandedPreview() {
    MyTuxtlaTheme {
        MyTuxtlaApp(windowSize = WindowWidthSizeClass.Expanded)
    }
}
