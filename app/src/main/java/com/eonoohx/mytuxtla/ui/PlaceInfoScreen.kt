package com.eonoohx.mytuxtla.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.data.local.PlacesStoreDataProvider
import com.eonoohx.mytuxtla.model.Place
import com.eonoohx.mytuxtla.ui.theme.MyTuxtlaTheme

@Composable
fun PlaceInfoScreen(place: Place, modifier: Modifier = Modifier) {
    Surface(modifier = modifier) {
        Column(verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)), modifier = Modifier.padding(
            dimensionResource(R.dimen.padding_medium)
        )) {
            Image(
                painter = painterResource(place.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.aspectRatio(16f / 9f)
            )
            Text(text = stringResource(place.name), style = MaterialTheme.typography.displaySmall)
            Text(text = stringResource(place.info), style = MaterialTheme.typography.bodyLarge)
        }
    }

}

@Preview(showBackground = true)
@Composable
fun PlaceInfoScreenPreview() {
    MyTuxtlaTheme {
        PlaceInfoScreen(
            place = PlacesStoreDataProvider.listOfPlaces[0],
            modifier = Modifier.fillMaxSize()
        )
    }
}