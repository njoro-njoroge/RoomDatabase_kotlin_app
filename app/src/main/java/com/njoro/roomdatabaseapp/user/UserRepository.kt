package com.njoro.roomdatabaseapp.user

import android.app.Application
import android.util.Log
import androidx.annotation.WorkerThread
import com.njoro.roomdatabaseapp.database.UserEntity
import com.njoro.roomdatabaseapp.database.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    // Observed Flow will notify the observer when the data has changed.
    val allUsers: Flow<List<UserEntity>> = userDao.getAllUsers()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(userEntity: UserEntity){    //The suspend modifier tells the compiler that this needs to be called from a coroutine or another suspending function.
      Log.e("INSERT PARAMS", userEntity.username)
        userDao.insert(userEntity)
    }

    suspend fun delete(){
        userDao.deleteAll()
    }
    suspend fun deleteUserById(userId: Long){
        userDao.deleteUserById(userId)
    }
}