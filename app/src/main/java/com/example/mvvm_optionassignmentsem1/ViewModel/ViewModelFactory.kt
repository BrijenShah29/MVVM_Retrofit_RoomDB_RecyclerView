package com.example.mvvm_optionassignmentsem1.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository

class ViewModelFactory(private val repository: PerfumeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}