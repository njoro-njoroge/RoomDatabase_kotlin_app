package com.njoro.roomdatabaseapp.user

import android.app.Application
import com.njoro.roomdatabaseapp.database.UserRoomDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


class UserApplication: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    /**
     * Using by lazy so the database and the repository are only created when they're needed
      * rather than when the application starts
     */
    val database by lazy { UserRoomDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { UserRepository(database.userDao()) }
}