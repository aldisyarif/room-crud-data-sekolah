package com.example.myformdatasiswaapp.usecase

import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.repository.IIndonesiaAreaRepository
import com.example.myformdatasiswaapp.utils.Resources
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class SaveSchoolUseCase(
    private val repository: IIndonesiaAreaRepository
) {

    operator fun invoke(school: SchoolEntity): Flow<Resources<Long>> {
        return flow {
            emit(Resources.loading())
            try {
                emit(Resources.success(repository.saveSchool(school)))
            } catch (e: Exception){
                emit(Resources.error(e.localizedMessage ?: "", null))
            }
        }.flowOn(Dispatchers.IO)
    }

}