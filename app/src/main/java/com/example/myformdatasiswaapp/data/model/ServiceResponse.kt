package com.example.myformdatasiswaapp.data.model

data class ProvinceResponse<T>(
    val provinsi: List<T>?
)

data class CityOrDistrictsResponse<T>(
    val kota_kabupaten: List<T>?
)

data class ProvinsiItem(
    val nama: String?,
    val id: Int?
)

data class CityOrDistrictsItem(
    val id: Int?,
    val id_provinsi: String?,
    val nama: String?
)
