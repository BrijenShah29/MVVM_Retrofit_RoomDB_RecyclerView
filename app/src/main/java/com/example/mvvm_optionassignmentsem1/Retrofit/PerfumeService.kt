package com.example.mvvm_optionassignmentsem1.Retrofit

import com.example.mvvm_optionassignmentsem1.Models.PerfumeList
import com.example.mvvm_optionassignmentsem1.Models.Product
import retrofit2.Response
import retrofit2.http.GET

interface PerfumeService {

    @GET("/products/category/fragrances")
    suspend fun getPerfumes():Response<PerfumeList>
}