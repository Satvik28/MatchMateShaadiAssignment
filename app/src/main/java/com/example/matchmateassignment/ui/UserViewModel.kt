package com.example.matchmateassignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.data.repository.MainUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: MainUserRepository
) : ViewModel() {

    val userList = repository.getUsers()
        .cachedIn(viewModelScope)

    fun changeUserStatus(uuid: String, userStatus: UserStatus) {
        viewModelScope.launch {
            repository.changeUserStatus(uuid, userStatus)
        }
    }

    companion object {
        const val TAG = "UserViewModel"
    }
}