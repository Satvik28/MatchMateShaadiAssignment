package com.example.matchmateassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.databinding.UserItemBinding
import com.example.matchmateassignment.utils.DiffUtil

class UserListAdapter(
    private val onAcceptClick: (UserProfileDbData) -> Unit,
    private val onDeclineClick: (UserProfileDbData) -> Unit
) : PagingDataAdapter<UserProfileDbData, UserProfileViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserProfileViewHolder(binding, onAcceptClick, onDeclineClick)
    }

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}