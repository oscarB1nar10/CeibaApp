package com.example.ceibaapp.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userId: Long,
    val name: String,
    val phone: String,
    val email: String
)