package com.gulfappdeveloper.project2.data.remote

object HttpRoutes {

    const val BASE_URL = ""

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

    const val SUBMIT_PRODUCT = "/api/purchase"

    const val STOCK_ADJUSTMENT = "/api/stockadjustment/"

    const val PRICE_ADJUSTMENT = "/api/priceupdate"
    const val PRODUCT_FOR_PRICE_ADJUSTMENT = "/api/priceupdate/"

    const val UNI_LICENSE_HEADER = "riolab123456"
    const val UNI_LICENSE_ACTIVATION_URL =
        "http://license.riolabz.com/license-repo/public/api/v1/verifyjson"

    const val SEE_IP4 = "https://ipinfo.io/ip"

    const val GET_BARCODE_DESIGNS = "/api/BarcodePrint"

}