package com.example.myformdatasiswaapp.data.remote

import com.example.myformdatasiswaapp.data.model.CityOrDistrictsItem
import com.example.myformdatasiswaapp.data.model.CityOrDistrictsResponse
import com.example.myformdatasiswaapp.data.model.ProvinceResponse
import com.example.myformdatasiswaapp.data.model.ProvinsiItem
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("daerahindonesia/provinsi")
    suspend fun getProvince() : ProvinceResponse<ProvinsiItem>

    @GET("daerahindonesia/kota")
    suspend fun getCityOrDistrict(
        @Query("id_provinsi") proviceId: String?
    ): CityOrDistrictsResponse<CityOrDistrictsItem>
}