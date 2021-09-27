package com.riad.pexelsdemoapp.data.api

import com.riad.pexelsdemoapp.data.models.PostResponse
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET(ApiEndPoints.POST)
    suspend fun getPost(): Response<PostResponse>

}