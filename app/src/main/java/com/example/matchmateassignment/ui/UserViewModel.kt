package com.example.matchmateassignment.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.data.repository.MainUserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: MainUserRepository
) : ViewModel() {

    val usersState: StateFlow<UiEvents<List<UserProfileDbData>>> =
        repository.getUsers()
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = UiEvents.Loading
            )


    fun changeUserStatus(uuid: String, userStatus: UserStatus) {
        viewModelScope.launch {
           repository.changeUserStatus(uuid, userStatus)
        }
    }

    companion object {
        const val TAG = "UserViewModel"
    }
}