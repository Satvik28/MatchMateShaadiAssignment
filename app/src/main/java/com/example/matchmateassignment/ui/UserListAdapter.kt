package com.example.matchmateassignment.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.databinding.UserItemBinding

class UserListAdapter(
    private val onAcceptClick: (UserProfileDbData) -> Unit,
    private val onDeclineClick: (UserProfileDbData) -> Unit
) : ListAdapter<UserProfileDbData, UserProfileViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserProfileViewHolder(binding, onAcceptClick, onDeclineClick)
    }

    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class DiffUtil : DiffUtil.ItemCallback<UserProfileDbData>() {
        override fun areItemsTheSame(
            oldItem: UserProfileDbData,
            newItem: UserProfileDbData
        ): Boolean {
            return oldItem.uuid == newItem.uuid
        }


        override fun areContentsTheSame(
            oldItem: UserProfileDbData,
            newItem: UserProfileDbData
        ): Boolean {
            return oldItem == newItem
        }

    }

}