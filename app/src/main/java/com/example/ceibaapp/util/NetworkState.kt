package com.example.ceibaapp.util

import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

class NetworkState @Inject constructor(connectivityManager: ConnectivityManager) {
    val connectivityManager = connectivityManager

    fun getNetworkState(): Boolean{
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true

    }
}