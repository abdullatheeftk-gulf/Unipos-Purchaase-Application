package com.gulfappdeveloper.project2.data.remote

import com.gulfappdeveloper.project2.domain.models.remote.Error
import com.gulfappdeveloper.project2.domain.models.remote.get.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.Product
import com.gulfappdeveloper.project2.domain.models.remote.get.WelcomeMessage
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.ProductGroup
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.TaxCategory
import com.gulfappdeveloper.project2.domain.models.remote.get.for_add_product.Units
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseRequestBody
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseResponse
import com.gulfappdeveloper.project2.domain.models.remote.get.login.User
import com.gulfappdeveloper.project2.domain.models.remote.get.price_adjustment.ProductForPriceAdjustment
import com.gulfappdeveloper.project2.domain.models.remote.get.see_ip.SeeIp
import com.gulfappdeveloper.project2.domain.models.remote.get.stock_adjustment.ProductStock
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.domain.models.remote.post.PurchaseClass
import com.gulfappdeveloper.project2.domain.models.remote.post.add_product.AddProduct
import com.gulfappdeveloper.project2.domain.models.remote.post.price_adjustment.PriceAdjustment
import com.gulfappdeveloper.project2.domain.models.remote.post.stoke_adjustment.StockAdjustment
import com.gulfappdeveloper.project2.domain.services.ApiService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ConnectException


class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {


    override suspend fun getWelcomeMessage(url: String): Flow<GetDataFromRemote<WelcomeMessage>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                // Log.e(TAG, " ConnectTimeoutException")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                // Log.e(TAG, " NoTransformationFoundException")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {

                //   Log.e(TAG, " ${e.message}")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun loginUser(url: String): Flow<GetDataFromRemote<User>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun uniLicenseActivation(
        rioLabKey: String,
        url: String,
        licenseRequestBody: LicenseRequestBody
    ): Flow<GetDataFromRemote<LicenseResponse>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    header("Authorization", rioLabKey)
                    setBody(licenseRequestBody)
                }

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        var str = ""
                        try {
                            str = httpResponse.bodyAsText()
                        } catch (e: Exception) {
                            e.message
                        }

                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description + "-" + str
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {

                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {

                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {

                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun searchClientDetails(url: String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getProductDetailsByName(url: String): Flow<GetDataFromRemote<List<Product>>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                //   Log.e(TAG, " No internet")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getProductDetailByBarcode(url: String): Flow<GetDataFromRemote<Product?>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(null)
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun addClientDetails(
        url: String,
        addClient: AddClient
    ): Flow<GetDataFromRemote<AddClient>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    setBody(addClient)
                }
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException or Json Convert Exception Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {

                // Log.e(TAG, " ${e.message}")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun addProduct(
        url: String,
        addProduct: AddProduct
    ): Flow<GetDataFromRemote<Product>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    setBody(addProduct)
                }
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        if (statusCode == 400) {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description + ", Duplicate Barcode"
                                    )
                                )
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        }
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException or Json Convert Exception Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {

                e.printStackTrace()
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getProductGroups(url: String): Flow<GetDataFromRemote<List<ProductGroup>>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun searchProductGroups(url: String): Flow<GetDataFromRemote<List<ProductGroup>>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getAllUnits(url: String): Flow<GetDataFromRemote<List<Units>>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getAllTaxCategories(url: String): Flow<GetDataFromRemote<List<TaxCategory>>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(emptyList())
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun purchaseFunction(
        url: String,
        purchaseClass: PurchaseClass
    ): Flow<GetDataFromRemote<PurchaseClass>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    setBody(purchaseClass)
                }

                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        if (statusCode == 400) {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        }
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException or Json Convert Exception Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getStockOfAProduct(url: String): Flow<GetDataFromRemote<ProductStock?>> {
        return flow {

            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        if (statusCode == 204) {
                            emit(
                                value = GetDataFromRemote.Success(null)
                            )
                        } else {
                            emit(
                                value = GetDataFromRemote.Success(httpResponse.body())
                            )
                        }
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun adjustStocksOfProductList(
        url: String,
        stockAdjustment: StockAdjustment
    ): Flow<GetDataFromRemote<StockAdjustment>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    setBody(stockAdjustment)
                }
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        if (statusCode == 400) {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = "Duplicate Barcode"
                                    )
                                )
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        }
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException or Json Convert Exception Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }


    override suspend fun getIp4Address(url: String): Flow<GetDataFromRemote<SeeIp>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun adjustProductPrice(
        url: String,
        priceAdjustment: PriceAdjustment
    ): Flow<GetDataFromRemote<PriceAdjustment>> {
        return flow {
            try {
                val httpResponse = client.post(urlString = url) {
                    contentType(ContentType.Application.Json)
                    setBody(priceAdjustment)
                }
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        if (statusCode == 400) {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        } else {
                            emit(
                                GetDataFromRemote.Failed(
                                    error = Error(
                                        code = statusCode,
                                        message = httpResponse.status.description
                                    )
                                )
                            )
                        }
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException or Json Convert Exception Server ok. other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                //   Log.e(TAG, " No internet")
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in mobile "
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }

    override suspend fun getProductForPriceAdjustment(url: String): Flow<GetDataFromRemote<ProductForPriceAdjustment>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)
                when (val statusCode = httpResponse.status.value) {
                    in 200..299 -> {
                        emit(
                            GetDataFromRemote.Success(httpResponse.body())
                        )
                    }
                    in 300..399 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 400..499 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    in 500..599 -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                    else -> {
                        emit(
                            GetDataFromRemote.Failed(
                                error = Error(
                                    code = statusCode,
                                    message = httpResponse.status.description
                                )
                            )
                        )
                    }
                }

            } catch (e: ConnectTimeoutException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 600,
                            message = "ConnectTimeoutException Server Down"
                        )
                    )
                )

            } catch (e: NoTransformationFoundException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 601,
                            message = "NoTransformationFoundException Server ok. Other problem"
                        )
                    )
                )
            } catch (e: ConnectException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 602,
                            message = "No internet in Mobile"
                        )
                    )
                )
            } catch (e: JsonConvertException) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 603,
                            message = "Json convert Exception $e"
                        )
                    )
                )
            } catch (e: Exception) {
                emit(
                    GetDataFromRemote.Failed(
                        error = Error(
                            code = 604,
                            message = "Other Exception $e"
                        )
                    )
                )
            }
        }
    }


}