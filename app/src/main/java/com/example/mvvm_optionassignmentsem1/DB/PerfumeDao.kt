package com.example.mvvm_optionassignmentsem1.DB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mvvm_optionassignmentsem1.Models.Product

@Dao
interface PerfumeDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPerfumes(perfumes : List<Product>)

    @Query("SELECT * FROM perfumes")
    suspend fun getPerfumes() : List<Product>
}