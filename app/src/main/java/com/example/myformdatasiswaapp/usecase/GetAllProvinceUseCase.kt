package com.example.myformdatasiswaapp.usecase

import com.example.myformdatasiswaapp.data.model.ProvinceResponse
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetAllProvinceUseCase(
    private val repository: IIndonesiaAreaRepository
) {
    operator fun invoke(): Flow<Resources<ProvinceResponse<ProvinsiItem>>> {
        return flow{
            emit(Resources.loading())
            try {
                val response = repository.getProvinces()
                emit(Resources.success(response))
            }catch (e: Exception){
                emit(Resources.error(e.localizedMessage ?: "Unknown Error", null))
            }
        }.flowOn(Dispatchers.IO)
    }
}