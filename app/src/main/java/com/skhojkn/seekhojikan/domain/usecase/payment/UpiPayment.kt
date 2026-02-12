package com.skhojkn.seekhojikan.domain.usecase.payment

import com.skhojkn.seekhojikan.domain.interfaces.PaymentProcessor
import javax.inject.Inject

class UpiPayment @Inject constructor(): PaymentProcessor {
    override fun pay(amount: Int) {

    }
}