package com.skhojkn.seekhojikan.domain.usecase.network

import kotlinx.coroutines.flow.Flow

interface NetworkMonitor {
    val isOnline: Flow<ConnectionState>
}