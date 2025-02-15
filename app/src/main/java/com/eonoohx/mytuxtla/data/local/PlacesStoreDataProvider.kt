package com.eonoohx.mytuxtla.data.local

import com.eonoohx.mytuxtla.R
import com.eonoohx.mytuxtla.data.PlaceCategoryType
import com.eonoohx.mytuxtla.model.Place
import com.eonoohx.mytuxtla.model.PlaceCategory

object PlacesStoreDataProvider {
    val defaultPlace = null

    val listOfPlaces = listOf(
        // Coffee Shops
        Place(
            R.string.aroma_espresso,
            R.string.aroma_espresso_info,
            PlaceCategoryType.COFFEE_SHOPS.name,
            R.drawable.aroma_espresso
        ),
        Place(
            R.string.cafe_san_carlos, R.string.cafe_san_carlos_info,
            PlaceCategoryType.COFFEE_SHOPS.name,
            R.drawable.cafe_san_carlos
        ),
        Place(
            R.string.cesmach_cafe, R.string.cesmach_cafe_info,
            PlaceCategoryType.COFFEE_SHOPS.name,
            R.drawable.cesmach_cafe
        ),

        // Restaurants
        Place(
            R.string.la_mansion,
            R.string.la_mansion_info,
            PlaceCategoryType.RESTAURANTS.name,
            R.drawable.la_mansion
        ),
        Place(
            R.string.condimento,
            R.string.condimento_info,
            PlaceCategoryType.RESTAURANTS.name,
            R.drawable.condimento
        ),
        Place(
            R.string.las_pichanchas,
            R.string.las_pichanchas_info,
            PlaceCategoryType.RESTAURANTS.name,
            R.drawable.las_pichanchas
        ),

        // Kid-Friendly Places
        Place(
            R.string.convivencia_infantil,
            R.string.convivencia_infantil_info,
            PlaceCategoryType.FAMILY_PLACES.name,
            R.drawable.convivencia_infantil
        ),
        Place(
            R.string.zoomat,
            R.string.zoomat_info,
            PlaceCategoryType.FAMILY_PLACES.name,
            R.drawable.zoomat
        ),
        Place(
            R.string.museo_marimba,
            R.string.museo_marimba_info,
            PlaceCategoryType.FAMILY_PLACES.name,
            R.drawable.museo_marimba
        ),

        // Parks
        Place(
            R.string.parque_marimba,
            R.string.parque_marimba_info,
            PlaceCategoryType.PARKS.name,
            R.drawable.parque_marimba
        ),
        Place(
            R.string.parque_joyyo_mayu,
            R.string.parque_joyyo_mayu_info,
            PlaceCategoryType.PARKS.name,
            R.drawable.parque_joyyo_mayu
        ),
        Place(
            R.string.parque_bicentenario,
            R.string.parque_bicentenario_info,
            PlaceCategoryType.PARKS.name,
            R.drawable.parque_bicentenario
        ),

        // Shopping Centers
        Place(
            R.string.galerias_boulevard,
            R.string.galerias_boulevard_info,
            PlaceCategoryType.SHOPPING_PLACES.name,
            R.drawable.galerias_boulevard
        ),
        Place(
            R.string.plaza_crystal,
            R.string.plaza_crystal_info,
            PlaceCategoryType.SHOPPING_PLACES.name,
            R.drawable.plaza_crystal
        ),
        Place(
            R.string.plaza_las_americas,
            R.string.plaza_las_americas_info,
            PlaceCategoryType.SHOPPING_PLACES.name,
            R.drawable.plaza_las_americas
        )
    )

    val listOfCategories = listOf(
        PlaceCategory(
            PlaceCategoryType.COFFEE_SHOPS.categoryPlace,
            R.drawable.aroma_espresso
        ),
        PlaceCategory(
            PlaceCategoryType.RESTAURANTS.categoryPlace,
            R.drawable.la_mansion
        ),
        PlaceCategory(
            PlaceCategoryType.FAMILY_PLACES.categoryPlace,
            R.drawable.convivencia_infantil
        ),
        PlaceCategory(
            PlaceCategoryType.PARKS.categoryPlace,
            R.drawable.parque_marimba
        ),
        PlaceCategory(
            PlaceCategoryType.SHOPPING_PLACES.categoryPlace,
            R.drawable.galerias_boulevard
        )
    )
}