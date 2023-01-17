package com.example.mvvm_optionassignmentsem1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_optionassignmentsem1.Models.PerfumeList
import com.example.mvvm_optionassignmentsem1.Models.Product
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PerfumeRepository) :ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getPerfume()
        }
    }
        val perfumes : LiveData<List<Product>>
            get() = repository.perfumes

}