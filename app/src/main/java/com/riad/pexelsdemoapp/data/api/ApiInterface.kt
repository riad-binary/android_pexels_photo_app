package com.riad.pexelsdemoapp.data.api

import com.riad.pexelsdemoapp.data.models.SearchPhotoResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET(ApiEndPoints.SEARCH)
    suspend fun getSearchedPhotos(@Query("query") query: String): Response<SearchPhotoResponse>

}