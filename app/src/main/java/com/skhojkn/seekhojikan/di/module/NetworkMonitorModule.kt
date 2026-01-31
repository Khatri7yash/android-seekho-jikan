package com.skhojkn.seekhojikan.di.module

import com.skhojkn.seekhojikan.data.remote.ConnectivityManagerNetworkMonitor
import com.skhojkn.seekhojikan.domain.usecase.network.NetworkMonitor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkMonitorModule {

    @Binds
    @Singleton
    abstract fun bindsNetworkMonitor(
        networkMonitor: ConnectivityManagerNetworkMonitor
    ): NetworkMonitor
}