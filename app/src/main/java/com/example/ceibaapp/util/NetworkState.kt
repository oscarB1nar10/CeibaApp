package com.example.ceibaapp.util

import android.net.ConnectivityManager
import android.net.NetworkInfo
import javax.inject.Inject

@Suppress("DEPRECATION")
class NetworkState @Inject constructor(private val connectivityManager: ConnectivityManager) {

    fun getNetworkState(): Boolean{
        val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnected == true

    }
}