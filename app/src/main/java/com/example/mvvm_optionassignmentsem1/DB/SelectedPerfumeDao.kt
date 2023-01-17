package com.example.mvvm_optionassignmentsem1.DB

import androidx.room.*
import com.example.mvvm_optionassignmentsem1.Models.Product
import com.example.mvvm_optionassignmentsem1.Models.SelectedPerfume

@Dao
interface SelectedPerfumeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPerfumes(perfumes : SelectedPerfume)

    @Query("DELETE FROM selectedPerfume")
    suspend fun deletePerfume()

    @Query("SELECT * FROM selectedPerfume")
    suspend fun getPerfumes() : SelectedPerfume
}