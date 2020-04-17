package com.mindorks.retrofit.coroutines.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.retrofit.coroutines.data.model.User
import com.mindorks.retrofit.coroutines.data.repository.MainRepository
import com.mindorks.retrofit.coroutines.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.UnknownHostException


class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {
    val usersData = MutableLiveData<Resource<MutableList<User>>>()
    init {
        getUsers()
    }

    private fun getMessage(e: Exception): String {
        var message = e.localizedMessage ?: "Unknown Error"
        if (e is UnknownHostException) {
            message = "Network error, please check your connectivity"
        }
        return message
    }

    private fun getUsers() {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val users = mainRepository.getUsers()
                withContext(Dispatchers.Main) {
                    usersData.value = Resource.success(users.toMutableList())
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    usersData.value = Resource.error(null, getMessage(e))
                }
            }
        }
    }
}