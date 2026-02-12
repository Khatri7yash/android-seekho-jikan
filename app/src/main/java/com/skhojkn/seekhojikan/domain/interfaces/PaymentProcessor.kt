package com.skhojkn.seekhojikan.domain.interfaces

interface PaymentProcessor {
    fun pay(amount: Int)
}