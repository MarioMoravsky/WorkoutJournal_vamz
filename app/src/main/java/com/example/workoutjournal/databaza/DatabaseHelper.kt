package com.example.workoutjournal.databaza
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

class DatabaseHelper private constructor(context: Context) {
    val db: AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "WorkoutJournalDB"
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



    fun userDao(): UserDao {
        return db.userDao()
    }
}