package com.gulfappdeveloper.project2.data.remote

import android.util.Log
import com.gulfappdeveloper.project2.domain.models.ClientDetails
import com.gulfappdeveloper.project2.domain.models.ProductDetails
import com.gulfappdeveloper.project2.domain.service.ApiService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.serialization.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

private const val TAG = "ApiServiceImpl"

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun getClientDetails(): Flow<List<ClientDetails>> {
        return flow {
            try {
                val httpResponse = client.get(HttpRoutes.GET_CLIENT_DETAILS)
                when (httpResponse.status.value) {
                    in 200..299 -> {
                        Log.d(TAG, "success ")
                        emit(httpResponse.body())
                    }
                    in 300..399 -> {
                        emit(
                            listOf(
                              ClientDetails(
                                  clientName = "300 Error",
                                  clientId = 25,
                                  taxId = "sdsff"
                              )
                            )
                        )
                        Log.w(TAG, "warning ")
                    }
                    in 400..499 -> {
                        emit(
                            listOf(
                                ClientDetails(
                                    clientName = "400 Error",
                                    clientId = 25,
                                    taxId = "sdsff"
                                )
                            )
                        )
                        Log.e(TAG, "error ")
                    }
                    else -> {
                        Log.i(TAG, "other")
                    }
                }

            } catch (e: JsonConvertException) {
                Log.e(TAG, " bbb  ${e.message} ")
                emit(
                    listOf(
                        ClientDetails(
                            clientName = "400 JsonConvertException",
                            clientId = 25,
                            taxId = "sdsff"
                        )
                    )
                )
            } catch (e: ConnectTimeoutException) {
                Log.w(TAG, "getProductDetails: connect time out exception")
                emit(
                    listOf(
                        ClientDetails(
                            clientName = "other ConnectTimeoutException",
                            clientId = 25,
                            taxId = "sdsff"
                        )
                    )
                )
            } catch (e: NoTransformationFoundException) {
                emit(
                    listOf(
                        ClientDetails(
                            clientName = "other NoTransformationFoundException",
                            clientId = 25,
                            taxId = "sdsff"
                        )
                    )
                )
            } catch (e: Exception) {

                emit(
                    listOf(
                        ClientDetails(
                            clientName = "other Error",
                            clientId = 25,
                            taxId = "sdsff"
                        )
                    )
                )
            }
        }
    }


    override suspend fun getProductDetails(): Flow<List<ProductDetails>> {
        return flow {
            try {
                val httpResponse = client.get(HttpRoutes.GET_PRODUCT_DETAILS)
                when (httpResponse.status.value) {
                    in 200..299 -> {
                        Log.d(TAG, "success ")
                        emit(httpResponse.body())
                    }
                    in 300..399 -> {
                        emit(
                            listOf(
                                ProductDetails(
                                    productId = 1,
                                    productName = "300 Foundation",
                                    productRate = 25.5f,
                                    unit = "Kg",
                                    stoke = 0f,
                                    vat = 0f
                                )
                            )
                        )
                        Log.w(TAG, "warning ")
                    }
                    in 400..499 -> {
                        emit(
                            listOf(
                                ProductDetails(
                                    productId = 1,
                                    productName = "400 Foundation",
                                    productRate = 25.5f,
                                    unit = "Kg",
                                    stoke = 0f,
                                    vat = 0f
                                )
                            )
                        )
                        Log.e(TAG, "error ")
                    }
                    else -> {
                        Log.i(TAG, "other")
                    }
                }

            } catch (e: JsonConvertException) {
                Log.e(TAG, " bbb  ${e.message} ")
                emit(
                    listOf(
                        ProductDetails(
                            productId = 1,
                            productName = "JsonConvertException",
                            productRate = 25.5f,
                            unit = "Kg",
                            stoke = 0f,
                            vat = 0f
                        )
                    )
                )
            } catch (e: ConnectTimeoutException) {
                Log.w(TAG, "getProductDetails: connect time out exception")
                emit(
                    listOf(
                        ProductDetails(
                            productId = 1,
                            productName = "error ConnectTimeoutException",
                            productRate = 25.5f,
                            unit = "Kg",
                            stoke = 0f,
                            vat = 0f
                        )
                    )
                )
            } catch (e: NoTransformationFoundException) {
                emit(
                    listOf(
                        ProductDetails(
                            productId = 1,
                            productName = "error NoTransformationFoundException",
                            productRate = 25.5f,
                            unit = "Kg",
                            stoke = 0f,
                            vat = 0f
                        )
                    )
                )
            } catch (e: Exception) {

                emit(
                    listOf(
                        ProductDetails(
                            productId = 1,
                            productName = "error Exception",
                            productRate = 25.5f,
                            unit = "Kg",
                            stoke = 0f,
                            vat = 0f
                        )
                    )
                )
            }
        }
    }
}