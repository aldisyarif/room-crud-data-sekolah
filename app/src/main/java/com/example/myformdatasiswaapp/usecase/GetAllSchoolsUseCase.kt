package com.example.myformdatasiswaapp.usecase

import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

class GetAllSchoolsUseCase(
    private val repository: IIndonesiaAreaRepository
) {
    operator fun invoke(): Flow<Resources<List<SchoolEntity>>> {
        return flow{
            emit(Resources.loading())
            try {
                val db = repository.getAllSchools()
                db.collect {
                    emit(Resources.success(it))
                }
            } catch (e: Throwable){
                emit(Resources.error(e.localizedMessage ?: "Unknown Error", null))
            }
        }.flowOn(Dispatchers.IO)
    }
}