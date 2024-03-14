package com.gulfappdeveloper.project2.usecases.remote_usecase.get.barcode_designs

import com.gulfappdeveloper.project2.repositories.RemoteRepository

class GetBarcodeDesignsUseCase(
    private val remoteRepository: RemoteRepository
) {
    suspend operator fun invoke(url:String) = remoteRepository.getBarcodeDesigns(url = url)

}