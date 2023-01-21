package com.gulfappdeveloper.project2.usecases.remote_usecase.get.license

import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseRequestBody
import com.gulfappdeveloper.project2.domain.models.remote.get.license.LicenseResponse
import com.gulfappdeveloper.project2.repositories.RemoteRepository
import kotlinx.coroutines.flow.Flow

class UniLicenseActivationUseCase(
    private val remoteRepository: RemoteRepository
) {

    suspend operator fun invoke(
        rioLabKey: String,
        url: String,
        licenseRequestBody: LicenseRequestBody
    ): Flow<GetDataFromRemote<LicenseResponse>> {
        return remoteRepository.uniLicenseActivation(
            rioLabKey = rioLabKey,
            url = url,
            licenseRequestBody = licenseRequestBody
        )
    }
}