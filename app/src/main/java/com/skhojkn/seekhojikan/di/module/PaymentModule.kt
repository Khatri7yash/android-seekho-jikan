package com.skhojkn.seekhojikan.di.module

import com.skhojkn.seekhojikan.domain.interfaces.PaymentProcessor
import com.skhojkn.seekhojikan.domain.usecase.payment.PaytmPayment
import com.skhojkn.seekhojikan.domain.usecase.payment.RazorPay
import com.skhojkn.seekhojikan.domain.usecase.payment.UpiPayment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import javax.inject.Singleton


//@Module
//@InstallIn(ActivityComponent::class)
//abstract class PaymentModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindPaytmPayment(paytmPayment: PaytmPayment): PaymentProcessor
//
//    @Binds
//    @Singleton
//    abstract fun bindUpiProcessor(upiPayment: UpiPayment): PaymentProcessor
//
//    @Binds
//    @Singleton
//    abstract fun bindRazorPayProcessor(razorPay: RazorPay): PaymentProcessor
//
//}