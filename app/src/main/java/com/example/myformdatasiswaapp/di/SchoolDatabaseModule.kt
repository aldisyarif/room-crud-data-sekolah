package com.example.myformdatasiswaapp.di

import android.content.Context
import androidx.room.Room
import com.example.myformdatasiswaapp.data.local.SchoolDao
import com.example.myformdatasiswaapp.data.local.SchoolDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SchoolDatabaseModule {

    @Singleton
    @Provides
    fun provideSchoolDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        SchoolDatabase::class.java,
        "Indonesia_School_db"
    ).build()

    @Singleton
    @Provides
    fun provideSchoolDao(
        db: SchoolDatabase
    ) = db.schoolDao()

}