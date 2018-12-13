package io.github.andyradionov.googlenews.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Andrey Radionov
 */
class NetworkManager(private val context: Context) {

    fun isInternetAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager?

        return connectivityManager?.let {
            with(connectivityManager) {
                activeNetworkInfo != null
                        && activeNetworkInfo.isAvailable
                        && activeNetworkInfo.isConnected
            }
        } ?: false
    }
}