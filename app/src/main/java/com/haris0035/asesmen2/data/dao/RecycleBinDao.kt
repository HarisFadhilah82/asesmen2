package com.haris0035.asesmen2.data.dao

import androidx.room.*
import com.haris0035.asesmen2.data.model.RecycleBin
import kotlinx.coroutines.flow.Flow

@Dao
interface RecycleBinDao {
    @Query("SELECT * FROM recycle_bin ORDER BY deletedAt DESC")
    fun getAllDeletedTasks(): Flow<List<RecycleBin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDeletedTask(task: RecycleBin)

    @Delete
    suspend fun permanentlyDeleteTask(task: RecycleBin)

    @Query("DELETE FROM recycle_bin")
    suspend fun clearRecycleBin()
} 