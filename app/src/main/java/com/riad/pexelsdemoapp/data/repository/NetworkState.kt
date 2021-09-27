package com.riad.pexelsdemoapp.data.repository

import com.riad.pexelsdemoapp.App
import com.riad.pexelsdemoapp.R

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

class NetworkState(val status: Status, val msg: String) {

    companion object {

        val LOADED: NetworkState
        val LOADING: NetworkState
        val ERROR: NetworkState
        val NO_CONNECTION: NetworkState

        init {
            LOADED = NetworkState(Status.SUCCESS, "Success")

            LOADING = NetworkState(Status.RUNNING, "Running")

            ERROR = NetworkState(Status.FAILED, App.instance.getString(R.string.error_network_failed))

            NO_CONNECTION = NetworkState(Status.FAILED, App.instance.getString(R.string.error_no_internet))

        }
    }
}