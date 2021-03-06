package com.mindorks.retrofit.coroutines.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.database.UserDatabase
import com.mindorks.retrofit.coroutines.data.repository.MainRepository
import com.mindorks.retrofit.coroutines.ui.main.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper, private val userDatabase: UserDatabase) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(MainRepository(apiHelper, userDatabase)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}

