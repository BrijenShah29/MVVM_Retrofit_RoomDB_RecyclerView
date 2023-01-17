package com.example.mvvm_optionassignmentsem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_optionassignmentsem1.Adapter.PerfumeAdapter
import com.example.mvvm_optionassignmentsem1.DB.PerfumeDatabase
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository
import com.example.mvvm_optionassignmentsem1.Retrofit.PerfumeService
import com.example.mvvm_optionassignmentsem1.Retrofit.RetrofitHelper
import com.example.mvvm_optionassignmentsem1.ViewModel.MainViewModel
import com.example.mvvm_optionassignmentsem1.ViewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val perfumeService = RetrofitHelper.getInstance().create(PerfumeService::class.java)
        val recyclerview = findViewById<RecyclerView>(R.id.recycler_View)
        val perfumeDatabase = PerfumeDatabase.getDatabase(this)
        val repository = PerfumeRepository(perfumeService,perfumeDatabase)

        val adapter = PerfumeAdapter(this,perfumeDatabase.selectedPerfume())



        mainViewModel = ViewModelProvider(this,ViewModelFactory(repository)).get(MainViewModel::class.java)
        recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerview.setHasFixedSize(true)
        recyclerview.adapter = adapter

        mainViewModel.perfumes.observe(this, Observer {
            Log.d("results",it.toString())
            adapter.submitList(it)
        })






    }
}