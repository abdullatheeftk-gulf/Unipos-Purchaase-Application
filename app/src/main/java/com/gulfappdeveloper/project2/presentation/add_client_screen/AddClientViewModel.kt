package com.gulfappdeveloper.project2.presentation.add_client_screen

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gulfappdeveloper.project2.data.remote.HttpRoutes
import com.gulfappdeveloper.project2.domain.models.remote.get.GetDataFromRemote
import com.gulfappdeveloper.project2.domain.models.remote.post.AddClient
import com.gulfappdeveloper.project2.presentation.add_client_screen.util.AddClientEvent
import com.gulfappdeveloper.project2.presentation.ui_util.UiEvent
import com.gulfappdeveloper.project2.usecases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "AddClientViewModel"

@HiltViewModel
class AddClientViewModel
@Inject constructor(
    private val useCase: UseCase
) : ViewModel() {

    private val _accountName = mutableStateOf("")
    val accountName: State<String> = _accountName

    private val _streetName = mutableStateOf("")
    val streetName: State<String> = _streetName

    private val _buildingNumber = mutableStateOf("")
    val buildingNumber: State<String> = _buildingNumber

    private val _plotIdentification = mutableStateOf("")
    val plotIdentification: State<String> = _plotIdentification

    private val _citySubDivisionName = mutableStateOf("")
    val citySubDivisionName: State<String> = _citySubDivisionName

    private val _cityName = mutableStateOf("")
    val cityName: State<String> = _cityName

    private val _postalZone = mutableStateOf("")
    val postalZone: State<String> = _postalZone

    private val _countrySubEntity = mutableStateOf("")
    val countrySubEntity: State<String> = _countrySubEntity

    private val _country = mutableStateOf("")
    val country: State<String> = _country

    private val _nat = mutableStateOf("")
    val nat: State<String> = _nat

    private val _phoneOne = mutableStateOf("")
    val phoneOne: State<String> = _phoneOne

    private val _phoneTwo = mutableStateOf("")
    val phoneTwo: State<String> = _phoneTwo

    private val _taxId = mutableStateOf("")
    val taxId: State<String> = _taxId


    fun setAccountName(value: String) {
        _accountName.value = value
    }

    fun setStreetName(value: String) {
        _streetName.value = value
    }

    fun setBuildingNumber(value: String) {
        _buildingNumber.value = value
    }

    fun setPlotIdentification(value: String) {
        _plotIdentification.value = value
    }

    fun setCitySubDivisionName(value: String) {
        _citySubDivisionName.value = value
    }

    fun setCityName(value: String) {
        _cityName.value = value
    }

    fun setPostalZone(value: String) {
        _postalZone.value = value
    }

    fun setCountrySubEntity(value: String) {
        _countrySubEntity.value = value
    }

    fun setCountry(value: String) {
        _country.value = value
    }

    fun setNat(value: String) {
        _nat.value = value
    }

    fun setPhoneOne(value: String) {
        _phoneOne.value = value
    }

    fun setPhoneTwo(value: String) {
        _phoneTwo.value = value
    }

    fun setTaxId(value: String) {
        _taxId.value = value
    }

    private val _addClientEvent = Channel<AddClientEvent>()
    val addClientEvent = _addClientEvent.receiveAsFlow()

    fun addClientFun(baseUrl: String) {
        sendAddClientEvent(UiEvent.ShowProgressBar)
        val url = baseUrl + HttpRoutes.GET_CLIENT_DETAILS
        val addClient = AddClient(
            accountName = _accountName.value,
            buildingNumber = _buildingNumber.value,
            cityName = _cityName.value,
            citySubdivisionName = _citySubDivisionName.value,
            country = _country.value,
            countrySubEntity = _countrySubEntity.value,
            nat = _nat.value,
            phoneOne = _phoneOne.value,
            phoneTwo = _phoneTwo.value,
            plotIdentification = _plotIdentification.value,
            postalZone = _postalZone.value,
            streetName = _streetName.value,
            taxId = _taxId.value
        )
        viewModelScope.launch(Dispatchers.IO) {
            useCase.addClientUseCase(
                url = url,
                addClient = addClient
            ).collectLatest { result ->
                sendAddClientEvent(UiEvent.CloseProgressBar)
                when (result) {
                    is GetDataFromRemote.Success -> {
                       // Log.i(TAG, "addClientFun: success ${result.data.accountId}")

                        // Account id will send through the navigation event
                        sendAddClientEvent(UiEvent.Navigate(route = result.data.accountId.toString()))
                    }
                    is GetDataFromRemote.Failed -> {
                        val error =
                            "Error is ${result.error.message} & code ${result.error.code} & url $url"
                        sendAddClientEvent(
                            UiEvent.ShowSnackBar(
                                message = error
                            )
                        )
                        Log.e(TAG, "addClientFun: $error")
                    }
                    else -> Unit
                }
            }
        }
    }


    private fun sendAddClientEvent(event: UiEvent) {
        viewModelScope.launch {
            _addClientEvent.send(AddClientEvent(event))
        }
    }


}