package com.odougle.callapimvvm.repository

import com.odougle.callapimvvm.model.UserResponse
import com.odougle.callapimvvm.network.ApiInterface
import com.odougle.callapimvvm.utils.Resource
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val apiInterface: ApiInterface
){
    suspend fun getUserResponse() : Resource<List<UserResponse>>{
        val response = try {
            apiInterface.getUserData()
        }catch (e: Exception){
            return Resource.Error("An unkown error occurred: ${e.localizedMessage}")
        }
        return Resource.Sucess(response)
    }
}