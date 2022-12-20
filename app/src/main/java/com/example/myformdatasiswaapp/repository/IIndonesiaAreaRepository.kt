package com.example.myformdatasiswaapp.repository

import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsResponse
import com.example.myformdatasiswaapp.data.model.ProvinceResponse
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import kotlinx.coroutines.flow.Flow

interface IIndonesiaAreaRepository {

    suspend fun getProvinces(): ProvinceResponse<ProvinsiItem>

    suspend fun getCityOrDistricts(
        provinceId: String?
    ): CityOrDistrictsResponse<CityOrDistrictsItem>

    suspend fun saveSchool(
        school: SchoolEntity
    ): Long

    suspend fun getAllSchools(): Flow<List<SchoolEntity>>

    suspend fun updateSchool(
        school: SchoolEntity
    ): Int

    suspend fun getDetailSchool(
        id: Int
    ): Flow<SchoolEntity>

    suspend fun deleteSchool(
        school: SchoolEntity
    ): Int
}