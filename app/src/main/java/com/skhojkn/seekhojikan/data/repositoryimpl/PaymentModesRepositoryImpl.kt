package com.skhojkn.seekhojikan.data.repositoryimpl

import com.skhojkn.seekhojikan.domain.repository.PaymentModesRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import com.skhojkn.seekhojikan.domain.usecase.payment.EnumPaymentModes
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentModesRepositoryImpl @Inject constructor(): PaymentModesRepository {
    override fun getPaymentModes(): Flow<Result<List<String>>> = flow {
        emit(Result.Loading)
        delay(2000)
        emit(Result.Success(EnumPaymentModes.entries.map { it.name }))
    }
}