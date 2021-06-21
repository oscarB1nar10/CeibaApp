package com.example.ceibaapp.business.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Comment(
    @PrimaryKey val commentId: Long,
    val userCreatorId: Long,
    val title: String,
    val description: String
)