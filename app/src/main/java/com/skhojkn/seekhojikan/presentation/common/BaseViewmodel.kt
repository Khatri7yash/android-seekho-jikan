package com.skhojkn.seekhojikan.presentation.common

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.skhojkn.seekhojikan.domain.usecase.network.NetworkMonitor
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BaseViewmodel @Inject constructor(
    application: Application,
    networkMonitor: NetworkMonitor
) : AndroidViewModel(application) {
    val isOnline = networkMonitor.isOnline
}