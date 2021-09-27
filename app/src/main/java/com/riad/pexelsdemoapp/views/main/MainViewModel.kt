package com.riad.pexelsdemoapp.views.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.riad.pexelsdemoapp.App
import com.riad.pexelsdemoapp.data.api.ApiClient
import com.riad.pexelsdemoapp.data.api.ApiInterface
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {

    val apiService : ApiInterface = ApiClient.getClient(App.instance)
    var job: Job? = null

    init {
        Log.e("rrr", "MainViewModel init")
    }

    fun getPost() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getPost()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e("rrr", "response.isSuccessful response:" + response.body())
                } else {
                    Log.e("rrr", "response.error response:" + response.message())
                }
            }
        }
    }


}