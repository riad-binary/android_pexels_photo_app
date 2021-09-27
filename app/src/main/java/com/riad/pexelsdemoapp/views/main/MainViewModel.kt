package com.riad.pexelsdemoapp.views.main

import android.util.Log
import androidx.lifecycle.ViewModel
import com.riad.pexelsdemoapp.App
import com.riad.pexelsdemoapp.data.api.ApiClient
import com.riad.pexelsdemoapp.data.api.ApiInterface
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val TAG: String = MainViewModel::class.java.getName()
    val apiService : ApiInterface = ApiClient.getClient(App.instance)
    var job: Job? = null

    init {
        Log.e(TAG, "MainViewModel init")
    }

    fun getSearchPhotos() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = apiService.getSearchedPhotos("nature")
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.e(TAG, "response.isSuccessful response:" + response.body())
                } else {
                    Log.e(TAG, "response.error response:" + response.message())
                }
            }
        }
    }


}