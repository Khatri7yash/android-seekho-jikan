package com.skhojkn.seekhojikan.presentation.screens.paymentmodes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import androidx.lifecycle.viewModelScope
import com.skhojkn.seekhojikan.domain.usecase.PaymentModesListUseCase
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaymentViewModel @Inject constructor(private val paymentModesListUseCase: PaymentModesListUseCase): ViewModel() {

    private val _paymentList = MutableStateFlow<com.skhojkn.seekhojikan.domain.usecase.network.Result<List<String>>>(com.skhojkn.seekhojikan.domain.usecase.network.Result.Loading)
    val paymentList: StateFlow<Result<List<String>>> = _paymentList.asStateFlow()

    fun getPaymentModes(){
        viewModelScope.launch {
            paymentModesListUseCase(null).collect{
                _paymentList.value = it
            }
        }

        liveData {  }.map {  }.switchMap {  }
    }

    fun continuePayment(){}

}