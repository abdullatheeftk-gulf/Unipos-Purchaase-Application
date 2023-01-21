package com.gulfappdeveloper.project2.presentation.purchase_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "PurchaseScreenViewModel"

@HiltViewModel
class PurchaseScreenViewModel @Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onFocused(value: Boolean) {
        if (value) {
            sendUiEvent(UiEvent.AnimateWithKeyBoard)
        } else {
            sendUiEvent(UiEvent.AnimateBackWithKeyBoard)
        }
    }


    private fun sendUiEvent(uiEvent: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(uiEvent)
        }
    }


}