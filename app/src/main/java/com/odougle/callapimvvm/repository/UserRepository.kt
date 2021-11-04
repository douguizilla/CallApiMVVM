package com.odougle.callapimvvm.repository

import com.odougle.callapimvvm.network.ApiInterface
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val apiInterface: ApiInterface
){
}