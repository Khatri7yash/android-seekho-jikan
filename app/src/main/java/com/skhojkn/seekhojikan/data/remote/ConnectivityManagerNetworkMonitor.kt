package com.skhojkn.seekhojikan.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import com.skhojkn.seekhojikan.domain.usecase.network.ConnectionState
import com.skhojkn.seekhojikan.domain.usecase.network.NetworkMonitor
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

class ConnectivityManagerNetworkMonitor @Inject constructor(
    @ApplicationContext private val context: Context
) : NetworkMonitor {

    override val isOnline: Flow<ConnectionState> = callbackFlow {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val callback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                trySend(ConnectionState.Available)
            }

            override fun onLost(network: Network) {
                trySend(ConnectionState.Unavailable)
            }
        }

        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest, callback)

        // Send initial state
        trySend(getCurrentState(connectivityManager))

        awaitClose {
            connectivityManager.unregisterNetworkCallback(callback)
        }
    }
    .distinctUntilChanged()

    private fun getCurrentState(connectivityManager: ConnectivityManager): ConnectionState {
        val network = connectivityManager.activeNetwork
        val isConnected = network?.let {
            connectivityManager.getNetworkCapabilities(it)
                ?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } == true
        return if (isConnected) ConnectionState.Available else ConnectionState.Unavailable
    }
}