package com.example.mvvm_optionassignmentsem1.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvm_optionassignmentsem1.Models.PerfumeList
import com.example.mvvm_optionassignmentsem1.Models.SelectedPerfume
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModelDetailActivity(private val repository: PerfumeRepository):ViewModel() {

        init {
            viewModelScope.launch(Dispatchers.IO) {
                repository.getSelectedPerfume()
            }
        }
        val selectedPerfumes : LiveData<SelectedPerfume>
            get() = repository.selectedPerfume

}