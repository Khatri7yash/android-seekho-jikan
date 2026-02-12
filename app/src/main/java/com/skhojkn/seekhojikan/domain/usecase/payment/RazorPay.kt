package com.skhojkn.seekhojikan.domain.usecase.payment

import com.skhojkn.seekhojikan.domain.interfaces.PaymentProcessor
import javax.inject.Inject

class RazorPay @Inject constructor(): PaymentProcessor {
    override fun pay(amount: Int) {

    }
}