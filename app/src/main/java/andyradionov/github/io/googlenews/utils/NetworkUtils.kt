package andyradionov.github.io.googlenews.utils

import android.content.Context
import android.net.ConnectivityManager

/**
 * @author Andrey Radionov
 */

fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            as ConnectivityManager

    return connectivityManager?.let {
        with(connectivityManager) {
            activeNetworkInfo != null
                    && activeNetworkInfo.isAvailable
                    && activeNetworkInfo.isConnected
        }
    }
}