package com.example.myformdatasiswaapp.usecase

import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class GetDetailSchoolUseCase(
    private val repository: IIndonesiaAreaRepository
) {
    operator fun invoke(schoolId: Int): Flow<Resources<SchoolEntity>> {
        return flow {
            emit(Resources.loading())
            try {
                val db = repository.getDetailSchool(schoolId)
                db.collect {
                    emit(Resources.success(it))
                }
            } catch (e: Exception){
                emit(Resources.error(e.localizedMessage ?: "", null))
            }
        }.flowOn(Dispatchers.IO)
    }
}