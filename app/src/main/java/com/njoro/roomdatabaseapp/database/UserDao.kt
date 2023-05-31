package com.njoro.roomdatabaseapp.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table ORDER BY username Desc")
    fun getAllUsers(): Flow<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(userEntity: UserEntity)

    @Query("DELETE  FROM user_table")
    suspend fun deleteAll()

    @Query("SELECT *  FROM user_table WHERE user_id = :userId")
    suspend fun deleteUserById(userId: Long): UserEntity

}