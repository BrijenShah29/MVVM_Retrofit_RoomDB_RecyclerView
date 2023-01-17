package com.example.mvvm_optionassignmentsem1.Typeconvertor

import androidx.room.TypeConverter
import com.example.mvvm_optionassignmentsem1.Models.Product

class TypeConvertor {

    @TypeConverter
    fun fromListToString(value : List<String>) : String{
        return value.joinToString("#")
    }
    @TypeConverter
    fun fromStringToList(value : String) : List<String>{
        return value.split("#")

    }

}