package com.riad.pexelsdemoapp.views.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.riad.pexelsdemoapp.App
import com.riad.pexelsdemoapp.data.api.ApiClient
import com.riad.pexelsdemoapp.data.api.ApiInterface
import com.riad.pexelsdemoapp.data.models.SearchPhotoResponse
import com.riad.pexelsdemoapp.data.repository.NetworkState
import com.riad.pexelsdemoapp.utils.NoConnectivityException
import kotlinx.coroutines.*

class MainViewModel : ViewModel() {
    private val TAG: String = MainViewModel::class.java.getName()

    val PER_PAGE = 30

    val apiService : ApiInterface = ApiClient.getClient(App.instance)
    val searchResponse =  MutableLiveData<SearchPhotoResponse>()
    val networkState  = MutableLiveData<NetworkState>()

    var job: Job? = null

    init {
        Log.e(TAG, "MainViewModel init" )
        getSearchPhotos("japan")
    }

    fun getSearchPhotos(query: String) {
        networkState.postValue(NetworkState.LOADING)
        job = CoroutineScope(Dispatchers.IO).launch {
            try{
                val response = apiService.getSearchedPhotos(query, PER_PAGE)
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        networkState.postValue(NetworkState.LOADED)
                        searchResponse.postValue(response.body())
//                        Log.e(TAG, "response.isSuccessful response:" + response.body())
                    } else {
                        networkState.postValue(NetworkState.ERROR)
                        Log.e(TAG, "response.error response:" + response.message())
                    }
                }
            }
            catch (it: Throwable){
                if (it is NoConnectivityException) {
                    Log.e(TAG, "response.error NoConnectivityException:")
                    networkState.postValue(NetworkState.NO_CONNECTION)
                } else {
                    networkState.postValue(NetworkState.ERROR)
                    Log.e(TAG, "getPost error: " + it.message)
                }
            }


        }
    }


}