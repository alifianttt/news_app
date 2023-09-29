package com.submission.newsapp.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.view.View
import android.widget.Toast

private fun Context.isInternetDataAvailable(): Boolean {
    val connectivityManager =
        getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    val networkCapabilities = connectivityManager.activeNetwork ?: return false
    val activeNetwork =
        connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

    return activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
}

fun capitalizeFirstLetter(input: String): String {
    return input.substring(0, 1).uppercase() + input.substring(1)
}

fun Context.toasShort(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.setVisible(visibele: Boolean){
    this.visibility = if (visibele) View.VISIBLE else View.GONE
}