package com.njoro.roomdatabaseapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
 data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    val userId: Long = 0,
   @ColumnInfo(name = "username")
   val username: String,
    @ColumnInfo(name = "email_address")
    val email: String,
    )



