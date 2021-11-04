package com.odougle.callapimvvm.network

import com.odougle.callapimvvm.model.UserResponse
import retrofit2.http.GET
import javax.inject.Singleton

@Singleton
interface ApiInterface {

    @GET("todos")
    suspend fun getUserData() : List<UserResponse>
}

