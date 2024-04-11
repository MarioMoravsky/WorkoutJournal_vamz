package com.example.workoutjournal.databaza

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TrainingRecordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val date: String,
    val exercise: String,
    val weight: Double,
    val sets: Int,
    val reps: Int
)