package com.example.workoutjournal.databaza
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val age: Int,
    val weight: Double,
    val height: Double
)