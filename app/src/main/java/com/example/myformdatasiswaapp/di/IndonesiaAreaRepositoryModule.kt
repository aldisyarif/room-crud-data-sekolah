package com.example.myformdatasiswaapp.di

import com.example.myformdatasiswaapp.data.local.SchoolDao
import com.example.myformdatasiswaapp.data.remote.ApiService
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.repository.IndonesiaAreaRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object IndonesiaAreaRepositoryModule {

    @Provides
    fun provideRepository(
        service: ApiService,
        db: SchoolDao
    ): IIndonesiaAreaRepository =
        IndonesiaAreaRepositoryImpl(service, db)
}