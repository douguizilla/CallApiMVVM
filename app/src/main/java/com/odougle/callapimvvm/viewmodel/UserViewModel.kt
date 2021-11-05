package com.odougle.callapimvvm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odougle.callapimvvm.model.UserResponse
import com.odougle.callapimvvm.repository.UserRepository
import com.odougle.callapimvvm.utils.Resource
import javax.inject.Inject

class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    var isLoaling = mutableStateOf(false)
    private var _getUserData: MutableLiveData<List<UserResponse>> = MutableLiveData<List<UserResponse>>()
    var getUserData: LiveData<List<UserResponse>> = _getUserData

    suspend fun getUserData() : Resource<List<UserResponse>>{

    }
}