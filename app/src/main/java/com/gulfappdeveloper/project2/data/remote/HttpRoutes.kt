package com.gulfappdeveloper.project2.data.remote

object HttpRoutes {

    const val BASE_URL = "https://uniposerpapi.azurewebsites.net"

    const val WELCOME_MESSAGE = "/api/oem"
    const val LOGIN = "/api/login/"

    const val GET_CLIENT_DETAILS = "/api/account"
    const val SEARCH_CLIENT_DETAILS = "/api/account/"


    const val GET_PRODUCT_DETAILS = "/api/productnamesearch/"
    const val PRODUCT_SEARCH_BY_BARCODE = "/api/productbarcodesearch/"

    const val GET_PRODUCT_GROUPS = "/api/productgroup/"
    const val GET_ALL_UNITS = "/api/unit"
    const val GET_ALL_TAX_CATEGORIES = "/api/tcategory"
    const val ADD_PRODUCT = "/api/product"

}