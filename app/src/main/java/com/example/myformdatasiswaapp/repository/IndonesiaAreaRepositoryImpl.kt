package com.example.myformdatasiswaapp.repository

import com.example.myformdatasiswaapp.data.local.SchoolDao
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsResponse
import com.example.myformdatasiswaapp.data.model.ProvinceResponse
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import com.example.myformdatasiswaapp.data.remote.ApiService
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class IndonesiaAreaRepositoryImpl(
    private val service: ApiService,
    private val db: SchoolDao
): IIndonesiaAreaRepository {
    override suspend fun getProvinces(): ProvinceResponse<ProvinsiItem> =
        service.getProvince()

    override suspend fun getCityOrDistricts(provinceId: String?): CityOrDistrictsResponse<CityOrDistrictsItem> =
        service.getCityOrDistrict(provinceId)

    override suspend fun saveSchool(school: SchoolEntity): Long =
        db.insertSchool(school)

    override suspend fun getAllSchools(): Flow<List<SchoolEntity>> =
        db.getAllSchools()

    override suspend fun updateSchool(school: SchoolEntity): Int =
        db.updateSchool(school)

    override suspend fun getDetailSchool(id: Int): Flow<SchoolEntity> =
        db.getDetailSchool(id)

    override suspend fun deleteSchool(school: SchoolEntity): Int =
        db.deleteSchool(school)


}