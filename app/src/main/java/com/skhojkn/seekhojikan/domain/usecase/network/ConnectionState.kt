package com.skhojkn.seekhojikan.domain.usecase.network

sealed class ConnectionState {
    object Available : ConnectionState()
    object Unavailable : ConnectionState()
}