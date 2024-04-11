package com.example.workoutjournal.databaza
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseHelper private constructor(context: Context) {
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database-name"
    )
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)

            }

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)

            }
        })
        .build()

    companion object {
        @Volatile
        private var INSTANCE: DatabaseHelper? = null

        fun getInstance(context: Context): DatabaseHelper {
            return INSTANCE ?: synchronized(this) {
                val instance = DatabaseHelper(context)
                INSTANCE = instance
                instance
            }
        }
    }

    // Metódy na manipuláciu s dátami
    fun insertUser(user: UserEntity) {
        db.userDao().insert(user)
    }

    fun getAllUsers(): List<UserEntity> {
        return db.userDao().getAll()
    }
}