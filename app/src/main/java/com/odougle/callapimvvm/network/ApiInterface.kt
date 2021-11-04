package com.odougle.callapimvvm.network

import com.odougle.callapimvvm.model.UserResponse
import retrofit2.http.GET

interface ApiInterface {

    @GET("todos")
    suspend fun getUserData() : List<UserResponse>
}

