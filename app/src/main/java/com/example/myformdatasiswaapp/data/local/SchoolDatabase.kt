package com.example.myformdatasiswaapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity

@Database(
    entities = [
        SchoolEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class SchoolDatabase: RoomDatabase() {

    abstract fun schoolDao(): SchoolDao

}