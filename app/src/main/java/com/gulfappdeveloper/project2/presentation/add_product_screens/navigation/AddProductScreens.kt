package com.gulfappdeveloper.project2.presentation.add_product_screens.navigation

sealed class AddProductScreens(val route: String) {
    object AddProductHomeScreen : AddProductScreens("add_product_home_screen")
    object SelectProductGroupScreen : AddProductScreens("select_product_group_screen")
    object MultiUnitScreen : AddProductScreens("multi_unit_screen")
}
