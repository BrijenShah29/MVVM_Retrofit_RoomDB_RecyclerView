package com.example.mvvm_optionassignmentsem1.Adapter

import android.app.Application
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_optionassignmentsem1.DB.PerfumeDatabase
import com.example.mvvm_optionassignmentsem1.DB.SelectedPerfumeDao
import com.example.mvvm_optionassignmentsem1.DetailsActivity
import com.example.mvvm_optionassignmentsem1.Models.Product
import com.example.mvvm_optionassignmentsem1.Models.SelectedPerfume
import com.example.mvvm_optionassignmentsem1.R
import com.example.mvvm_optionassignmentsem1.Repository.PerfumeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class PerfumeAdapter(var context: Context, private val selectedPerfumeDao: SelectedPerfumeDao) : ListAdapter<Product, PerfumeAdapter.PerfumeViewHolder>(diffutil()) {



    class PerfumeViewHolder (view: View) : RecyclerView.ViewHolder(view){
        val name = view.findViewById<TextView>(R.id.productName)
        val description = view.findViewById<TextView>(R.id.productDescription)
        val rating = view.findViewById<TextView>(R.id.productRating)
        val details = view.findViewById<TextView>(R.id.productDetails)
        val thumbImage = view.findViewById<ImageView>(R.id.thumbImage)
        var brand = view.findViewById<TextView>(R.id.productBrandName)


        fun bind(item : Product,context: Context,selectedPerfumeDao: SelectedPerfumeDao){

            name.text = item.title
            description.text = item.description
            rating.text = item.rating.toString() + " ★ "
            brand.text = item.brand
            Glide.with(context).load(item.thumbnail).into(thumbImage)


            details.setOnClickListener {
                GlobalScope.launch(Dispatchers.IO) {
                    val newList = SelectedPerfume(0,item.brand,item.category,item.description,item.images,item.price,item.rating,item.thumbnail,item.title)
                    selectedPerfumeDao.deletePerfume()
                    selectedPerfumeDao.addPerfumes(newList)
                }
                val intent = Intent(context,DetailsActivity::class.java)

                //intent.putExtra("name",item.title)
                //intent.putExtra("description",item.description)
                //intent.putExtra("rating",item.rating)
                //intent.putExtra("brand",item.brand)
                context.startActivity(intent)


            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PerfumeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardlayoutdesign,parent,false)
        return PerfumeViewHolder(view)

    }


    override fun onBindViewHolder(holder: PerfumeViewHolder, position: Int) {
    val item = getItem(position)
        holder.bind(item,context,selectedPerfumeDao)


    }

    class diffutil : DiffUtil.ItemCallback<Product>() {

        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
           return oldItem.id == newItem.id
        }


        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }
}