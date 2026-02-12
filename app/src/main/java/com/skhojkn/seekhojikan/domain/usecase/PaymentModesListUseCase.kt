package com.skhojkn.seekhojikan.domain.usecase

import com.skhojkn.seekhojikan.domain.repository.PaymentModesRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class PaymentModesListUseCase @Inject constructor(val repository: PaymentModesRepository): BaseUseCase<Any, Flow<Result<List<String>>>>() {
    override suspend fun invoke(params: Any?): Flow<Result<List<String>>> = repository.getPaymentModes()
}