package com.gulfappdeveloper.project2.data.remote

object HttpRoutes {

    private const val BASE_URL = "http://10.0.2.2:4000"
    const val GET_CLIENT_DETAILS = "$BASE_URL/api/clientList"
    const val GET_PRODUCT_DETAILS = "$BASE_URL/api/productList"

}