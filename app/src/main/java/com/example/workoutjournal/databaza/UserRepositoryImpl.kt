package com.example.workoutjournal.databaza

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val userDao: UserDao) : UserRepository {
    override fun getAllUsersStream(): Flow<List<UserEntity>> = userDao.getAll()

    override fun getUserStream(id: Int): Flow<UserEntity?> = userDao.getUserById(id)

    override suspend fun insertUser(user: UserEntity) {
        withContext(Dispatchers.IO) {
            userDao.insert(user)
        }
    }

    override suspend fun deleteUser(user: UserEntity) = userDao.delete(user)

    override suspend fun updateUser(user: UserEntity) = userDao.update(user)
}