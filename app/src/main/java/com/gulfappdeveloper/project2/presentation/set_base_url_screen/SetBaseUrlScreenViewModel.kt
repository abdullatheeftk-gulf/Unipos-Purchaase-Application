package com.gulfappdeveloper.project2.presentation.set_base_url_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.BuildConfig
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.datastore.UniLicenseDetails
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SetBaseUrlScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun setBaseUrl(value: String) {
        viewModelScope.launch {
            getWelcomeMessage(value = value)
        }
    }

    private fun getWelcomeMessage(value: String) {
        val url = value + HttpRoutes.WELCOME_MESSAGE
        sendUiEvent(UiEvent.ShowProgressBar)
        viewModelScope.launch {
            useCase.getWelcomeMessageUseCase(url = url).collectLatest { result ->
                sendUiEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    Log.e("Test", "getWelcomeMessage: success", )
                    useCase.saveBaseUrlUseCase(baseUrl = value)

                    sendUiEvent(UiEvent.Navigate(route = RootNavScreens.LoginScreen.route))
                }
                if (result is GetDataFromRemote.Failed) {
                    Log.d("Test", "getWelcomeMessage: Failed ${result.error}")
                    sendUiEvent(UiEvent.ShowSnackBar(message = "This Server with $url is down"))
                }
            }
        }
    }

   /* private fun readUniLicenseKeyDetails() {
        viewModelScope.launch {
            useCase.uniLicenseReadUseCase().collectLatest { value ->
                // checking for saved license details
                if (value.isNotEmpty() && value.isNotBlank()) {
                    val licenseDetails = Json.decodeFromString<UniLicenseDetails>(value)
                    // check saved license is demo
                    if (licenseDetails.licenseType == "demo" && !licenseDetails.expiryDate.isNullOrBlank() && licenseDetails.expiryDate.isNotEmpty()) {

                        // check for license expired
                        if (!isUniposLicenseExpired(licenseDetails.expiryDate)) {
                            // demo license not expired
                            sendUiEvent(UiEvent.Navigate(route = RootNavScreens.LoginScreen.route))
                        } else {
                            // demo license expired
                            sendUiEvent(UiEvent.Navigate(route = RootNavScreens.UniLicenseActivationScreen.route))
                        }
                    }
                    if (licenseDetails.licenseType == "permanent") {
                        // license is permanent
                        sendUiEvent(UiEvent.Navigate(route = RootNavScreens.LoginScreen.route))
                    }
                } else {
                    sendUiEvent(UiEvent.Navigate(route = RootNavScreens.UniLicenseActivationScreen.route))
                }
            }
        }
    }*/

   /* private fun isUniposLicenseExpired(eDate: String): Boolean {
        return try {
            val expDate: Date = SimpleDateFormat(
                "dd-MM-yyyy",
                Locale.getDefault()
            ).parse(eDate)!!

            expDate >= Date()
        }catch (e:Exception){
            true
        }
    }*/

    fun onErrorUrl(url: String) {
        sendUiEvent(UiEvent.ShowSnackBar(message = "Typed url $url is in wrong format"))
    }


    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}