package com.example.matchmateassignment.ui

import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmateassignment.R
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.databinding.UserItemBinding
import com.example.matchmateassignment.di.LocalDataBaseEntryPoint
import com.google.android.material.color.MaterialColors

class UserProfileViewHolder(
    private val binding: UserItemBinding,
    private val onAcceptClick: (UserProfileDbData) -> Unit,
    private val onDeclineClick: (UserProfileDbData) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(
        user: UserProfileDbData?,
    ) {
        with(binding) {
            user?.let { user ->
                userName.text = user.getFullName()
                userCity.text = user.getCityState()
                userAge.text = itemView.context.getString(R.string.user_age_string, user.age)

                Glide.with(itemView.context)
                    .load(user.pictureUrl)
                    .placeholder(R.drawable.sharp_person_3_24)
                    .circleCrop()
                    .into(userImage)

                val color = when (user.status) {
                    UserStatus.DEFAULT -> MaterialColors.getColor(
                        itemView,
                        com.google.android.material.R.attr.colorSurface
                    )

                    UserStatus.ACCEPTED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.light_green
                    )

                    UserStatus.DECLINED -> ContextCompat.getColor(
                        itemView.context,
                        R.color.light_red
                    )
                }
                acceptButton.isVisible = user.status != UserStatus.ACCEPTED
                declineButton.isVisible = user.status != UserStatus.DECLINED

                val score =
                    LocalDataBaseEntryPoint.getMatchScore(itemView.context.applicationContext)
                        .getScore(user)

                binding.matchScore.text =
                    itemView.context.getString(
                        R.string.match_score_format, score
                    )

                userCard.setCardBackgroundColor(color)

                acceptButton.setOnClickListener {
                    onAcceptClick(user)
                }

                declineButton.setOnClickListener {
                    onDeclineClick(user)
                }

            }
        }
    }
}