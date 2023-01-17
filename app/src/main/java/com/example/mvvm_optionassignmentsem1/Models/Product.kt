package com.example.mvvm_optionassignmentsem1.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "perfumes")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val brand: String,
    val category: String,
    val description: String,
    val images: List<String>,
    val price: Int,
    val rating: Double,
    val thumbnail: String,
    val title: String
)