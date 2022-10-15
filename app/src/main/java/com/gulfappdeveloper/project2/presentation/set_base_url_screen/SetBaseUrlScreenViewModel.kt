package com.gulfappdeveloper.project2.presentation.set_base_url_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.navigation.root.RootNavScreens
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetBaseUrlScreenViewModel @Inject constructor(
    private val useCase: UseCase
):ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun setBaseUrl(value:String){
        viewModelScope.launch {
            useCase.saveBaseUrlUseCase(baseUrl = value)
            getWelcomeMessage(url = value)
        }
    }

    private fun getWelcomeMessage(url:String){
        sendUiEvent(UiEvent.ShowProgressBar)
        viewModelScope.launch {
            useCase.getWelcomeMessageUseCase(url = url).collectLatest { result->
                sendUiEvent(UiEvent.CloseProgressBar)
                if (result is GetDataFromRemote.Success){
                    sendUiEvent(UiEvent.Navigate(RootNavScreens.HomeScreen.route))
                }
                if(result is GetDataFromRemote.Failed){
                    sendUiEvent(UiEvent.ShowSnackBar(message = "This Server with $url is down"))
                }
            }
        }
    }

    fun onErrorUrl(url:String){
        sendUiEvent(UiEvent.ShowSnackBar(message = "Typed url $url is in wrong format"))
    }


    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }
}