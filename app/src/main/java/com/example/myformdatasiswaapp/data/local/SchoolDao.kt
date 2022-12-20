package com.example.myformdatasiswaapp.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.flow.Flow

@Dao
interface SchoolDao {

    @Insert
    suspend fun insertSchool(schoolData: SchoolEntity): Long

    @Update
    suspend fun updateSchool(schoolData: SchoolEntity): Int

    @Delete
    suspend fun deleteSchool(schoolData: SchoolEntity): Int

    @Query("SELECT * FROM tb_school_entities")
    fun getAllSchools(): Flow<List<SchoolEntity>>

    @Query("SELECT * FROM tb_school_entities WHERE dataId = :id")
    fun getDetailSchool(id: Int): Flow<SchoolEntity>
}