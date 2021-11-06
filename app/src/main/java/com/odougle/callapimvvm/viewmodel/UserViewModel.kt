package com.odougle.callapimvvm.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.odougle.callapimvvm.model.UserResponse
import com.odougle.callapimvvm.repository.UserRepository
import com.odougle.callapimvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
): ViewModel(){
    var isLoading = mutableStateOf(false)
    private var _getUserData: MutableLiveData<List<UserResponse>> = MutableLiveData<List<UserResponse>>()
    var getUserData: LiveData<List<UserResponse>> = _getUserData

    suspend fun getUserData() : Resource<List<UserResponse>>{
        val result = userRepository.getUserResponse()
        if(result is Resource.Sucess){
            isLoading.value = true
            _getUserData.value = result.data!!
        }
        return result
    }
}