package com.example.workoutjournal

import com.example.workoutjournal.databaza.TrainingRecordEntity

class StatisticsCalculator(private val records: List<TrainingRecordEntity>) {

    fun averageWeight(): Double {
        return records.map { it.weight }.average()
    }

    fun totalSets(): Int {
        return records.sumOf { it.sets }
    }

    fun totalReps(): Int {
        return records.sumOf { it.reps }
    }

    fun totalVolume(): Int {
        return records.sumOf { it.sets * it.reps }
    }
}