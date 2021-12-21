package com.example.mvvmexample.data.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmexample.utils.NoInternetException
import okhttp3.Interceptor
import okhttp3.Response

@RequiresApi(Build.VERSION_CODES.M)
class NetworkConnectionInterceptor(
    context: Context
) : Interceptor{

    private val applicationContext = context.applicationContext

    override fun intercept(chain: Interceptor.Chain): Response {
        if(!isInternetAvailable())
            throw NoInternetException("Please check the internet connection and try again")

        return chain.proceed(chain.request())
    }

    private fun isInternetAvailable() : Boolean{
        val connectivityManager =
            applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var result = false

        val networkCapabilities = connectivityManager.activeNetwork ?: return false

        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        result = when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }

        return result
    }
}