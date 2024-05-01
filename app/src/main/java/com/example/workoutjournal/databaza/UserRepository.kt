package com.example.workoutjournal.databaza

import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getAllUsersStream(): Flow<List<UserEntity>>
    fun getUserStream(id: Int): Flow<UserEntity?>
    suspend fun insertUser(user: UserEntity)
    suspend fun deleteUser(user: UserEntity)
    suspend fun updateUser(user: UserEntity)
}
