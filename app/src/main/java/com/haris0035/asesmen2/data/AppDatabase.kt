package com.haris0035.asesmen2.data

import android.content.Context
import androidx.databinding.adapters.Converters
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.haris0035.asesmen2.data.dao.RecycleBinDao
import com.haris0035.asesmen2.data.dao.TaskDao
import com.haris0035.asesmen2.data.model.Category
import com.haris0035.asesmen2.data.model.RecycleBin
import com.haris0035.asesmen2.data.model.Task
import com.haris0035.mobpro1.data.dao.CategoryDao


@Database(entities = [Task::class, RecycleBin::class, Category::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun taskDao(): TaskDao
    abstract fun recycleBinDao(): RecycleBinDao
    abstract fun categoryDao(): CategoryDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "task_database"
                ).fallbackToDestructiveMigration()
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
} 