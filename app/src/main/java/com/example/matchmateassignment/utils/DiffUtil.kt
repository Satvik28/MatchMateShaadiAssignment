package com.example.matchmateassignment.utils

import androidx.recyclerview.widget.DiffUtil
import com.example.matchmateassignment.data.local.UserProfileDbData

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