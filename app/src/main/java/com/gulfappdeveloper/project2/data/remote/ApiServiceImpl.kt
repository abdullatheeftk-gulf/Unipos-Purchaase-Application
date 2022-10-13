package com.gulfappdeveloper.project2.data.remote

import android.util.Log
import com.gulfappdeveloper.project2.domain.models.remote.ClientDetails
import com.gulfappdeveloper.project2.domain.models.remote.Error
import com.gulfappdeveloper.project2.domain.models.remote.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.ProductDetails
import com.gulfappdeveloper.project2.domain.services.ApiService
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.network.sockets.*
import io.ktor.client.request.*
import io.ktor.serialization.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.net.ConnectException

private const val TAG = "ApiServiceImpl"

class ApiServiceImpl(
    private val client: HttpClient
) : ApiService {
    override suspend fun getClientDetails(url:String): Flow<GetDataFromRemote<List<ClientDetails>>> {
        return flow {
            try {
                val httpResponse = client.get(urlString = url)
                val statusCode = httpResponse.status.value
                Log.i(TAG, "status code $statusCode")

                when(statusCode){
                    in 200..299->{
                        emit(
                            GetDataFromRemote.Success<List<ClientDetails>>(httpResponse.body<List<ClientDetails>>())
                        )
                    }
                    in 300..399->{
                        emit(GetDataFromRemote.Failed(error = Error(
                            code = statusCode,
                            message = httpResponse.status.description                        )))
                    }
                    in 400..499->{
                        emit(GetDataFromRemote.Failed(error = Error(
                            code = statusCode,
                            message = httpResponse.status.description
                        )))
                    }
                    in 500..599->{
                        emit(GetDataFromRemote.Failed(error = Error(
                            code = statusCode,
                            message = httpResponse.status.description
                        )))
                    }
                    else->{
                        emit(GetDataFromRemote.Failed(error = Error(
                            code = statusCode,
                            message = httpResponse.status.description
                        )))
                    }
                }

            } catch (e: ConnectTimeoutException) {
                Log.e(TAG, " ConnectTimeoutException")
                emit(GetDataFromRemote.Failed(error = Error(
                    code = 600,
                    message = "ConnectTimeoutException Server Down"
                )))

            } catch (e: NoTransformationFoundException) {
                Log.e(TAG, " NoTransformationFoundException")
                emit(GetDataFromRemote.Failed(error = Error(
                    code = 601,
                    message = "NoTransformationFoundException Server ok. Other problem"
                )))
            } catch (e: ConnectException) {
                Log.e(TAG, " No internet")
                emit(GetDataFromRemote.Failed(error = Error(
                    code = 602,
                    message = "No internet in Mobile"
                )))
            } catch (e: JsonConvertException) {

                Log.e(TAG, " ${e.message}")
                emit(GetDataFromRemote.Failed(error = Error(
                    code = 603,
                    message = "Json convert Exception $e"
                )))
            } catch (e: Exception) {

                Log.e(TAG, " ${e.message}")
                emit(GetDataFromRemote.Failed(error = Error(
                    code = 604,
                    message = "Other Exception $e"
                )))
            }
        }
    }

    override suspend fun getProductDetails(url:String): Flow<GetDataFromRemote<List<ProductDetails>>> {
        return flow {

                try {
                    val httpResponse = client.get(urlString = url)
                    val statusCode = httpResponse.status.value
                    Log.i(TAG, "status code $statusCode")
                    when(statusCode){
                        in 200..299->{
                            emit(
                                GetDataFromRemote.Success<List<ProductDetails>>(httpResponse.body<List<ProductDetails>>())
                            )
                        }
                        in 300..399->{
                            emit(GetDataFromRemote.Failed(error = Error(
                                code = statusCode,
                                message = httpResponse.status.description
                            )))
                        }
                        in 400..499->{
                            emit(GetDataFromRemote.Failed(error = Error(
                                code = statusCode,
                                message = httpResponse.status.description
                            )))
                        }
                        in 500..599->{
                            emit(GetDataFromRemote.Failed(error = Error(
                                code = statusCode,
                                message = httpResponse.status.description
                            )))
                        }
                        else->{
                            emit(GetDataFromRemote.Failed(error = Error(
                                code = statusCode,
                                message = httpResponse.status.description
                            )))
                        }
                    }

                } catch (e: ConnectTimeoutException) {
                    Log.e(TAG, " ConnectTimeoutException")
                    emit(GetDataFromRemote.Failed(error = Error(
                        code = 600,
                        message = "ConnectTimeoutException Server Down"
                    )))

                } catch (e: NoTransformationFoundException) {
                    Log.e(TAG, " NoTransformationFoundException")
                    emit(GetDataFromRemote.Failed(error = Error(
                        code = 601,
                        message = "NoTransformationFoundException Server ok. other problem"
                    )))
                } catch (e: ConnectException) {
                    Log.e(TAG, " No internet")
                    emit(GetDataFromRemote.Failed(error = Error(
                        code = 602,
                        message = "No internet in mobile "
                    )))
                } catch (e: JsonConvertException) {

                    Log.e(TAG, " ${e.message}")
                    emit(GetDataFromRemote.Failed(error = Error(
                        code = 603,
                        message = "Json convert Exception $e"
                    )))
                } catch (e: Exception) {

                    Log.e(TAG, " ${e.message}")
                    emit(GetDataFromRemote.Failed(error = Error(
                        code = 604,
                        message = "Other Exception $e"
                    )))
                }
            }
        }

}