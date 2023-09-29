package com.submission.newsapp.ext

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object NetworkUtil {
    private val isInternetAvailableLiveData = MutableLiveData<Boolean>()

    fun isInternetDataAvailable(context: Context): LiveData<Boolean> {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return MutableLiveData(false)
        val activeNetwork =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return MutableLiveData(false)

        val isAvailable = activeNetwork.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        isInternetAvailableLiveData.postValue(isAvailable)
        return isInternetAvailableLiveData
    }
}