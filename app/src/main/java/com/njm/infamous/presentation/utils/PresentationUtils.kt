package com.njm.infamous.presentation.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast

object PresentationUtils {

    /*fun Context?.isOnline(failBlock : () -> Unit, successBlock : () -> Unit ) {
        this?.apply {
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo = cm.activeNetworkInfo
            if (netInfo != null && netInfo.isConnected){
                successBlock()
            }else{
                failBlock()
            }
        }?:failBlock()
    }*/
    var isConnected: Boolean = false

    fun isOnline(context: Context){
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
        isConnected = activeNetwork?.isConnectedOrConnecting == true
        Toast.makeText(context, "CONECTADO: $isConnected", Toast.LENGTH_SHORT).show()

    }
}