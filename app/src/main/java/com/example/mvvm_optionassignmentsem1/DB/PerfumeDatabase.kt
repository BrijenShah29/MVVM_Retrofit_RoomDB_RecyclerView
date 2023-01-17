package com.example.mvvm_optionassignmentsem1.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mvvm_optionassignmentsem1.Models.Product
import com.example.mvvm_optionassignmentsem1.Models.SelectedPerfume
import com.example.mvvm_optionassignmentsem1.Typeconvertor.TypeConvertor

@Database(entities = [Product::class,SelectedPerfume::class], version = 1)
@TypeConverters(TypeConvertor::class)
abstract class PerfumeDatabase : RoomDatabase() {

    abstract fun perfumeDao(): PerfumeDao
    abstract fun selectedPerfume() : SelectedPerfumeDao

    companion object{
        @Volatile
        private var INSTANCE : PerfumeDatabase? = null

        fun getDatabase(context: Context) : PerfumeDatabase{
            synchronized(this){

            if(INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context,PerfumeDatabase::class.java,"PerfumeDB").build()
                }
            }

            return INSTANCE!!
        }


    }



}