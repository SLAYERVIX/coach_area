package com.example.domain.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "client_table")
@Parcelize
data class Client(
    @PrimaryKey(autoGenerate = true)
    val _id: Int,
    var name: String,
    var age: Int,
    var phone: String,
    var length: Double,
    var muscle: Double,
    var weight: Double,
    var fat: Double,
    var disease: String,
    var imageUri: String
) : Parcelable
