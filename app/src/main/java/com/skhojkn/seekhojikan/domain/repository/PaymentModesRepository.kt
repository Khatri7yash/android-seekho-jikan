package com.skhojkn.seekhojikan.domain.repository

import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow

interface PaymentModesRepository{
    fun getPaymentModes(): Flow<Result<List<String>>>
}