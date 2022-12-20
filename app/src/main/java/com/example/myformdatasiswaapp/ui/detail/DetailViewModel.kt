package com.example.myformdatasiswaapp.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.enum.RequestStatus
import com.example.myformdatasiswaapp.usecase.DeleteSchoolUseCase
import com.example.myformdatasiswaapp.usecase.GetDetailSchoolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getDetailSchoolUseCase: GetDetailSchoolUseCase,
    private val deleteSchoolUseCase: DeleteSchoolUseCase
): ViewModel() {
    private val _detailSchool = MutableStateFlow<SchoolEntity?>(null)
    val detailSchool = _detailSchool.asStateFlow()

    private val _deleteSchoolSuccess = MutableStateFlow<Boolean?>(null)
    val deleteSchoolSuccess = _deleteSchoolSuccess.asStateFlow()


    fun getDetailSchool(id: Int) {
        viewModelScope.launch {
            getDetailSchoolUseCase(id).collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {}
                    RequestStatus.SUCCESS -> {
                        _detailSchool.emit(it.data)
                    }
                    RequestStatus.ERROR -> {}
                }
            }
        }
    }

    fun deleteSchool(schoolEntity: SchoolEntity){
        viewModelScope.launch {
            deleteSchoolUseCase(schoolEntity).collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {
                        _deleteSchoolSuccess.emit(false)
                    }
                    RequestStatus.SUCCESS -> {
                        _deleteSchoolSuccess.emit(true)
                    }
                    RequestStatus.ERROR -> {
                        _deleteSchoolSuccess.emit(false)
                    }
                }
            }
        }
    }
}