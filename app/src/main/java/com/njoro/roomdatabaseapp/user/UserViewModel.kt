package com.njoro.roomdatabaseapp.database

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.njoro.roomdatabaseapp.user.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository): ViewModel() {

    // You then converted the Flow to LiveData by calling asLiveData()
    val allUsers: LiveData<List<UserEntity>> = repository.allUsers.asLiveData()

    // Coroutines to insert the data
    fun insert(userEntity: UserEntity) = viewModelScope.launch {
        repository.insert(userEntity)
    }

    fun deleteAll() = viewModelScope.launch {

        repository.delete()
    }
}
    class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return UserViewModel(repository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

    }
/**
 * By using viewModels and ViewModelProvider.Factory,the framework will take
 * care of the lifecycle of the ViewModel. It will survive configuration
 * changes and even if the Activity is recreated, you'll always get the
 * right instance of the UserViewModel class.
 */