package com.mindorks.retrofit.coroutines.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val avatar: String,
    @PrimaryKey
    val email: String,
    val id: String,
    val name: String
)