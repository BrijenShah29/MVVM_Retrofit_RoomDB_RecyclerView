package com.example.mvvm_optionassignmentsem1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.mvvm_optionassignmentsem1.DB.PerfumeDatabase
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository
import com.example.mvvm_optionassignmentsem1.Retrofit.PerfumeService
import com.example.mvvm_optionassignmentsem1.Retrofit.RetrofitHelper
import com.example.mvvm_optionassignmentsem1.ViewModel.MainViewModelDetailActivity
import com.example.mvvm_optionassignmentsem1.ViewModel.ViewModelFactoryDetailActivity

class DetailsActivity : AppCompatActivity() {

    lateinit var mainViewModelDetailActivity: MainViewModelDetailActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        val title = findViewById<TextView>(R.id.title_DetailActivity)
        val description = findViewById<TextView>(R.id.description_DetailActivity)
        val brand = findViewById<TextView>(R.id.brand_DetailActivity)
        val image = findViewById<ImageView>(R.id.image_DetailActivity)
        val rating = findViewById<TextView>(R.id.rating_DetailActivity)
        val image1 = findViewById<ImageView>(R.id.image1_DetailActivity)
        val image2 = findViewById<ImageView>(R.id.image2_DetailActivity)
        val image3 = findViewById<ImageView>(R.id.image3_DetailActivity)





        val perfumeService = RetrofitHelper.getInstance().create(PerfumeService::class.java)
        val perfumeDatabase = PerfumeDatabase.getDatabase(this)


        val repository = PerfumeRepository(perfumeService,perfumeDatabase)
        mainViewModelDetailActivity = ViewModelProvider(this,ViewModelFactoryDetailActivity(repository)).get(MainViewModelDetailActivity::class.java)


        mainViewModelDetailActivity.selectedPerfumes.observe(this, Observer {

            title.text = it.title
            description.text = it.description
            rating.text = it.rating.toString()
            brand.text=it.brand
            Glide.with(this).load(it.thumbnail).into(image)
            Glide.with(this).load(it.images[0]).into(image1)
            Glide.with(this).load(it.images[1]).into(image2)
            Glide.with(this).load(it.images[2]).into(image3)
            Log.d("selected",it.toString())
        })



    }
}