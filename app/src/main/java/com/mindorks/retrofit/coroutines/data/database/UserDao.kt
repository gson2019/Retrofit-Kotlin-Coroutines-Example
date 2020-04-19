package com.mindorks.retrofit.coroutines.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.mindorks.retrofit.coroutines.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUsers(users: List<User>)

    @Query("SELECT * from users")
    suspend fun getAllUsers(): List<User>

    @Query("DELETE from users")
    suspend fun deleteAllUsers()
}