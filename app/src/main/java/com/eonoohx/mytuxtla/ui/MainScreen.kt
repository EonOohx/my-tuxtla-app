package com.eonoohx.mytuxtla.ui

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.data.local.PlacesStoreDataProvider
import com.eonoohx.mytuxtla.model.MenuItem
import com.eonoohx.mytuxtla.ui.components.ResizableText
import com.eonoohx.mytuxtla.ui.theme.MyTuxtlaTheme

@Composable
fun MainScreen(
    gridItems: List<MenuItem>,
    modifier: Modifier = Modifier,
    onCategoryPressed: (MenuItem) -> Unit,
) {
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
                onClick = {
                    onCategoryPressed(item)
                }
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
fun MainAppPreview() {
    MyTuxtlaTheme {
        MainScreen(
            gridItems = PlacesStoreDataProvider.listOfCategories,
            modifier = Modifier.fillMaxSize()
        ) {}
    }
}