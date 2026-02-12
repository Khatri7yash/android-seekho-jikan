package com.skhojkn.seekhojikan.domain.usecase

import com.skhojkn.seekhojikan.data.local.entity.AnimeEntity
import com.skhojkn.seekhojikan.domain.repository.PaymentModesRepository
import com.skhojkn.seekhojikan.domain.usecase.network.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

//class PaymentMethods @Inject constructor(val repository: PaymentModesRepository): BaseUseCase<Any, Flow<Result<List<String>>>>() {
//    override suspend fun invoke(params: Any?): Flow<Result<List<String>>> = flow{
//        emit(Result.Success(repository.getPaymentModes()))
//    }
//}