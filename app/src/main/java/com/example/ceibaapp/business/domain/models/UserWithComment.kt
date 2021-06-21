package com.example.ceibaapp.business.domain.models

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithComment(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "userCreatorId"
    )
    val commentlist: List<Comment>
)