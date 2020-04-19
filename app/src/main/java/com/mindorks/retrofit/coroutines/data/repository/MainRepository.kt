package com.mindorks.retrofit.coroutines.data.repository

import android.util.Log
import com.mindorks.retrofit.coroutines.data.api.ApiHelper
import com.mindorks.retrofit.coroutines.data.database.DbHelper
import com.mindorks.retrofit.coroutines.data.database.UserDao
import com.mindorks.retrofit.coroutines.data.database.UserDatabase
import com.mindorks.retrofit.coroutines.data.model.User
import java.lang.Exception

class MainRepository(private val apiHelper: ApiHelper, userDatabase: UserDatabase) {

    suspend fun getUsers() = apiHelper.getUsers()
    private val userDao = userDatabase.userDao

    suspend fun saveUsers(users: List<User>) : String? {
        return try {
            userDao.insertUsers(users)
            null
        } catch (e: Exception) {
            e.toString()
        }
    }

    suspend fun deleteLocalUsers() {
        userDao.deleteAllUsers()
    }

    suspend fun getLocalUsers(): List<User> {
        Log.d("Resource", "Retrieve users from DB")
        return userDao.getAllUsers()
    }
    init {

    }
}