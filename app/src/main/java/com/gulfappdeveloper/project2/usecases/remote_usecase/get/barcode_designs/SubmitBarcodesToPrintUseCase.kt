package com.gulfappdeveloper.project2.usecases.remote_usecase.get.barcode_designs

import com.gulfappdeveloper.project2.domain.models.barcode_print.BarcodePrintSubmit
import com.gulfappdeveloper.project2.repositories.RemoteRepository

class SubmitBarcodesToPrintUseCase(
    private val remoteRepository: RemoteRepository
) {

     suspend operator fun invoke(url:String,barcodePrintSubmit: BarcodePrintSubmit) =
         remoteRepository.submitBarcodesToPrint(
             url = url, barcodePrintSubmit = barcodePrintSubmit
         )
}