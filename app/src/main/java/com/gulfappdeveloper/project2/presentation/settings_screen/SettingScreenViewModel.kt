package com.gulfappdeveloper.project2.presentation.settings_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.firebase.FirebaseError
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SettingScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {
    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    //firebase
    private val collectionName = "ErrorData"

    fun setBaseUrl(value: String) {
        sendUiEvent(UiEvent.ShowProgressBar)
        viewModelScope.launch {
            useCase.saveBaseUrlUseCase(baseUrl = value)
            getWelcomeMessage(url = value + HttpRoutes.WELCOME_MESSAGE)
        }
    }


    private fun getWelcomeMessage(url: String) {

        viewModelScope.launch {
            useCase.getWelcomeMessageUseCase(url = url).collectLatest { result ->
                sendUiEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success) {
                    sendUiEvent(UiEvent.Navigate("pop"))
                }
                if (result is GetDataFromRemote.Failed) {
                    sendUiEvent(UiEvent.ShowSnackBar(message = "This Server with $url is down"))
                    useCase.insertErrorDataToFireStoreUseCase(
                        collectionName = collectionName,
                        documentName = "SettingScreen,getWelcomeMessage,${Date()}",
                        errorData = FirebaseError(
                            errorCode = result.error.code,
                            errorMessage = result.error.message ?: "",
                            url = url,
                            ipAddress = ""
                        )
                    )
                }
            }
        }
    }

    fun onErrorUrl(url: String) {
        sendUiEvent(UiEvent.ShowSnackBar(message = "Typed url $url is in wrong format"))
    }

    fun setLogout(){
        sendUiEvent(UiEvent.Navigate(route = RootNavScreens.LoginScreen.route))
    }


    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}