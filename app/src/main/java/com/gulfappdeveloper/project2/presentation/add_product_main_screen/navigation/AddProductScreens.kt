package com.gulfappdeveloper.project2.presentation.add_product_main_screen.navigation

sealed class AddProductScreens(val route:String){
    object AddProductHomeScreen: AddProductScreens("add_product_home_screen")
    object SelectProductGroupScreen: AddProductScreens("select_product_group_screen")
}
