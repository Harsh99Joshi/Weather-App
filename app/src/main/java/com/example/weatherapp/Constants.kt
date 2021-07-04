package com.example.weatherapp

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.core.content.getSystemService

object Constants{

    const val APP_ID: String ="b04c638f55f3914a17b6edafc2bcf3db"
    const val BASE_URL: String="http://api.openweathermap.org/data/"
    const val METRIC_UNIT: String="metric"

    const val PREFERENCE_NAME = "WeatherAppPreference"
    const val WEATHER_RESPONSE_DATA = "weather_response_data"



    fun isNetworkAvailable(context: Context):Boolean{
        val connectivityManager = context.getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            val network = connectivityManager.activeNetwork?: return false
            val activenetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

            return when{
                activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return true
                activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return true
                activenetwork.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> return true
                else -> return false
            }
        }

        else{
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnectedOrConnecting
        }
    }
}