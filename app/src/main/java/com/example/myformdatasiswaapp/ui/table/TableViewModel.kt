package com.example.myformdatasiswaapp.ui.table

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.enum.RequestStatus
import com.example.myformdatasiswaapp.usecase.GetAllSchoolsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TableViewModel @Inject constructor(
    private val getAllSchoolsUseCase: GetAllSchoolsUseCase
): ViewModel() {

    private val _listSchool = MutableStateFlow<List<SchoolEntity>?>(null)
    val listSchool = _listSchool.asStateFlow()


//    init {
//        getListSchool()
//    }

    fun getListSchool(){
        viewModelScope.launch {
            getAllSchoolsUseCase().collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {}
                    RequestStatus.SUCCESS -> {
                        _listSchool.emit(it.data)
                    }
                    RequestStatus.ERROR -> {

                    }
                }
            }
        }
    }
}