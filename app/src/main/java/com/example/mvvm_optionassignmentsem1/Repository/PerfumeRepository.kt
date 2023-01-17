package com.example.mvvm_optionassignmentsem1.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mvvm_optionassignmentsem1.DB.PerfumeDatabase
import com.example.mvvm_optionassignmentsem1.DB.SelectedPerfumeDao
import com.example.mvvm_optionassignmentsem1.Models.PerfumeList
import com.example.mvvm_optionassignmentsem1.Models.Product
import com.example.mvvm_optionassignmentsem1.Models.SelectedPerfume
import com.example.mvvm_optionassignmentsem1.Retrofit.PerfumeService

class PerfumeRepository(private val perfumeService: PerfumeService, private val perfumeDatabase: PerfumeDatabase){

    private val perfumeLiveData = MutableLiveData<List<Product>>()

    val perfumes : LiveData<List<Product>>
        get() = perfumeLiveData

    suspend fun getPerfume() {
        if (perfumeDatabase.perfumeDao().getPerfumes().size < 5) {
            val result = perfumeService.getPerfumes()

            if (result?.body() != null) {

                perfumeDatabase.perfumeDao().addPerfumes(result.body()!!.products)
                perfumeLiveData.postValue(result.body()!!.products)

            }
        }
        else
        {
            val result = perfumeDatabase.perfumeDao().getPerfumes()
            perfumeLiveData.postValue(result)
        }
    }




    private val selectedPerfumeLiveData = MutableLiveData<SelectedPerfume>()

    val selectedPerfume  : LiveData<SelectedPerfume>
        get() = selectedPerfumeLiveData

    suspend fun getSelectedPerfume() {
        val result = perfumeDatabase.selectedPerfume().getPerfumes()
        perfumeDatabase.selectedPerfume().addPerfumes(result)
        selectedPerfumeLiveData.postValue(result)

    }

}