package com.gulfappdeveloper.project2.presentation.sales_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.comon_memmory.CommonMemory
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.services.SalesService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "SalesScreenViewModel"
@HiltViewModel
class SalesScreenViewModel @Inject constructor(
    private val salesService: SalesService,
    private val commonMemory: CommonMemory,
):ViewModel() {

    private var baseUrl:String = ""

    // Welcome message
    private val _message = mutableStateOf("")
    val message: State<String> = _message

    init {
        baseUrl = commonMemory.baseUrl
        getWelcomeMessage()
    }

    private  fun getWelcomeMessage() {
        viewModelScope.launch {
            salesService.getWelcomeMessage(url = "$baseUrl/api/oem").collectLatest { result->
                
                when(result){
                    is GetDataFromRemote.Loading->{
                        
                    }

                    is GetDataFromRemote.Failed -> {}
                  
                    is GetDataFromRemote.Success ->{
                        Log.e(TAG, "getWelcomeMessage: ${result.data}", )
                        _message.value = result.data.message
                    }
                }
            }
        }
    }
}