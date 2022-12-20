package com.example.myformdatasiswaapp.data.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tb_school_entities")
data class SchoolEntity(

    @PrimaryKey(autoGenerate = true)
    val dataId: Int = 0,

    @ColumnInfo(name = "imageSchool", typeAffinity = ColumnInfo.BLOB)
    val image: ByteArray? = null,

    @ColumnInfo(name = "school_type")
    val schoolType: String,

    @ColumnInfo(name = "school_name")
    val schoolName: String,

    @ColumnInfo(name = "school_address")
    val schoolAddress: String,

    @ColumnInfo(name = "school_province")
    val schoolProvince: String,

    @ColumnInfo(name = "school_ZIP_code")
    val schoolZipCode: String,

    @ColumnInfo(name = "school_city_district")
    val schoolCityDistrict: String,

    @ColumnInfo(name = "school_no_hp")
    val schoolNoHp: String,

    @ColumnInfo(name = "school_email")
    val schoolEmail: String,

    @ColumnInfo(name = "school_facebook")
    val schoolFacebook: String,

    @ColumnInfo(name = "school_total_student")
    val schoolTotalStudent: String,

    ): Parcelable
