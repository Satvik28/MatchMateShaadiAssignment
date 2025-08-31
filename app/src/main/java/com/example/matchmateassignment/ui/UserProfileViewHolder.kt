package com.example.matchmateassignment.ui

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.matchmateassignment.R
import com.example.matchmateassignment.data.local.UserProfileDbData
import com.example.matchmateassignment.data.local.UserStatus
import com.example.matchmateassignment.databinding.UserItemBinding
import com.example.matchmateassignment.utils.MatchScore

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
                userName.text = user.fullName
                userCity.text = String.format("%s, %s", user.city, user.state)
                userAge.text = itemView.context.getString(R.string.user_age_string, user.age)

                Glide.with(itemView.context)
                    .load(user.pictureUrl)
                    .placeholder(R.drawable.sharp_person_3_24)
                    .circleCrop()
                    .into(userImage)

                val color = when (user.status) {
                    UserStatus.DEFAULT -> R.color.white
                    UserStatus.ACCEPTED -> R.color.light_green
                    UserStatus.DECLINED -> R.color.light_red
                }

                val score = MatchScore.getMatchScore(user)
                binding.matchScore.text =
                    itemView.context.getString(
                        R.string.match_score_format, score
                    )

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
}