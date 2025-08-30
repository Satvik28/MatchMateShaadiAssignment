package com.example.matchmateassignment.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmateassignment.R
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.databinding.UserItemBinding

class UserProfileViewHolder(
    private val binding: UserItemBinding,
    private val onAcceptClick: (UserProfileDbData) -> Unit,
    private val onDeclineClick: (UserProfileDbData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        user: UserProfileDbData,
    ) {
        with(binding) {
            userName.text = user.fullName
            userCity.text = user.city


            Glide.with(itemView.context)
                .load(user.pictureUrl)
                .circleCrop()
                .into(userImage)

            val color = when (user.status) {
                UserStatus.DEFAULT -> R.color.white
                UserStatus.ACCEPTED -> R.color.light_green
                UserStatus.DECLINED -> R.color.light_red
            }
            // TODO score logic

            userCard.setCardBackgroundColor(ContextCompat.getColor(userCard.context, color))

            acceptButton.setOnClickListener {
                onAcceptClick(user)
            }

            declineButton.setOnClickListener {
                onDeclineClick(user)
            }
        }
    }
}