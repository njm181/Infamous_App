package com.njm.infamous

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.njm.infamous.presentation.utils.PresentationUtils


class MainActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PresentationUtils.isOnline(this)
    }

    override fun onResume() {
        super.onResume()
        PresentationUtils.isOnline(this)
    }

}


