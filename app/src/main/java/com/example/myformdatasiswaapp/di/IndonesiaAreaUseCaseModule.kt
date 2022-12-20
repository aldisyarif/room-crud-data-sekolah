package com.example.myformdatasiswaapp.di

import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object IndonesiaAreaUseCaseModule {

    @Provides
    fun provideGetAllProvinceUseCase(
        repository: IIndonesiaAreaRepository
    ): GetAllProvinceUseCase =
        GetAllProvinceUseCase(repository)

    @Provides
    fun provideGetAllCityOrDistrictsUseCase(
        repository: IIndonesiaAreaRepository
    ): GetAllCityOrDistrictsUseCase =
        GetAllCityOrDistrictsUseCase(repository)

    @Provides
    fun provideSaveSchoolUseCase(
        repository: IIndonesiaAreaRepository
    ): SaveSchoolUseCase =
        SaveSchoolUseCase(repository)

    @Provides
    fun provideGetAllSchoolsUseCase(
        repository: IIndonesiaAreaRepository
    ): GetAllSchoolsUseCase =
        GetAllSchoolsUseCase(repository)

    @Provides
    fun providePutSchoolUseCase(
        repository: IIndonesiaAreaRepository
    ): PutSchoolUseCase =
        PutSchoolUseCase(repository)

    @Provides
    fun provideGetDetailSchoolUseCase(
        repository: IIndonesiaAreaRepository
    ): GetDetailSchoolUseCase =
        GetDetailSchoolUseCase(repository)

    @Provides
    fun provideDeleteSchoolUseCase(
        repository: IIndonesiaAreaRepository
    ): DeleteSchoolUseCase =
        DeleteSchoolUseCase(repository)
}