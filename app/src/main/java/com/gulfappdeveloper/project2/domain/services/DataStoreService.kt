package com.gulfappdeveloper.project2.domain.services

import kotlinx.coroutines.flow.Flow

interface DataStoreService {

    suspend fun updateOperationCount()
    suspend fun saveBaseUrl(baseUrl:String)
    suspend fun updateSerialNo()
    suspend fun saveIpAddress(ipAddress: String)
    suspend fun saveUniLicenseData(uniLicenseString: String)
    suspend fun saveDeviceId(deviceId:String)


    fun readOperationCount():Flow<Int>
    fun readBaseUrl():Flow<String>
    fun readSerialNo(): Flow<Int>
    fun readIpaddress(): Flow<String>
    fun readUniLicenseData(): Flow<String>
    fun readDeviceId():Flow<String>

}