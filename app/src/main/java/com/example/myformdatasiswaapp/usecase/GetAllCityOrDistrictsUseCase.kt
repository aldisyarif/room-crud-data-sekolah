package com.example.myformdatasiswaapp.usecase

import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsResponse
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllCityOrDistrictsUseCase(
    private val repository: IIndonesiaAreaRepository
) {
    operator fun invoke(provinceId: String): Flow<Resources<CityOrDistrictsResponse<CityOrDistrictsItem>>> {
        return flow {
            emit(Resources.loading())
            try {
                val response = repository.getCityOrDistricts(provinceId)
                emit(Resources.success(response))
            } catch (e: Exception){
                emit(Resources.error(e.localizedMessage ?: "Unknown Error", null))
            }
        }.flowOn(Dispatchers.IO)
    }
}