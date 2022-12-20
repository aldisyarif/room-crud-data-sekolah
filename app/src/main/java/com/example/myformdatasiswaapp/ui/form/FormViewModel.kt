package com.example.myformdatasiswaapp.ui.form

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myformdatasiswaapp.data.local.entity.SchoolEntity
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsResponse
import com.example.myformdatasiswaapp.data.model.ProvinceResponse
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import com.example.myformdatasiswaapp.enum.RequestStatus
import com.example.myformdatasiswaapp.usecase.GetAllCityOrDistrictsUseCase
import com.example.myformdatasiswaapp.usecase.GetAllProvinceUseCase
import com.example.myformdatasiswaapp.usecase.PutSchoolUseCase
import com.example.myformdatasiswaapp.usecase.SaveSchoolUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormViewModel @Inject constructor(
    private val getAllProvinceUseCase: GetAllProvinceUseCase,
    private val getAllCityOrDistrictsUseCase: GetAllCityOrDistrictsUseCase,
    private val saveSchoolUseCase: SaveSchoolUseCase,
    private val putSchoolUseCase: PutSchoolUseCase
): ViewModel() {

    private val _listProvince = MutableStateFlow<ProvinceResponse<ProvinsiItem>?>(null)
    val listProvince = _listProvince.asStateFlow()

    private val _listCityOrDistrict = MutableStateFlow<CityOrDistrictsResponse<CityOrDistrictsItem>?>(null)
    val listCityOrDistrict = _listCityOrDistrict.asStateFlow()

    private val _saveSchoolSuccess = MutableStateFlow<Boolean?>(null)
    val saveSchoolSuccess get() = _saveSchoolSuccess.asStateFlow()

    private val _updateSchoolSuccess = MutableStateFlow<Boolean?>(null)
    val updateSchoolSuccess get() = _updateSchoolSuccess.asStateFlow()


    fun getProvince(){
        viewModelScope.launch {
            getAllProvinceUseCase().collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {}
                    RequestStatus.SUCCESS -> {
                        _listProvince.emit(it.data)
                    }
                    RequestStatus.ERROR -> {

                    }
                }
            }
        }
    }

    fun getCityOrDistricts(provinceId: Int){
        viewModelScope.launch {
            getAllCityOrDistrictsUseCase(provinceId.toString()).collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {}
                    RequestStatus.SUCCESS -> {
                        _listCityOrDistrict.emit(it.data)
                    }
                    RequestStatus.ERROR -> {}
                }
            }
        }
    }

    fun saveSchoolData(schoolEntity: SchoolEntity){
        viewModelScope.launch {
            saveSchoolUseCase(schoolEntity).collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {
                        _saveSchoolSuccess.emit(false)
                    }
                    RequestStatus.SUCCESS -> {
                        if (it.data != null) _saveSchoolSuccess.emit(true)
                    }
                    RequestStatus.ERROR -> {
                        _saveSchoolSuccess.emit(false)
                    }
                }
            }
        }
    }

    fun updateSchoolData(schoolEntity: SchoolEntity){
        viewModelScope.launch {
            putSchoolUseCase(schoolEntity).collect {
                when(it.requestStatus){
                    RequestStatus.LOADING -> {
                        _updateSchoolSuccess.emit(false)
                    }
                    RequestStatus.SUCCESS -> {
                        if (it.data != null) _updateSchoolSuccess.emit(true)
                    }
                    RequestStatus.ERROR -> {
                        _updateSchoolSuccess.emit(false)
                    }
                }
            }
        }
    }

}