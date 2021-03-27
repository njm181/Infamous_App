package com.njm.infamous.domain.util

import androidx.lifecycle.MutableLiveData
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.presentation.utils.Resource
import java.time.DayOfWeek
import java.util.*

class DBConverters {

    val gson = Gson()

    companion object {

        @TypeConverter
        @JvmStatic
        fun genreIdToInt(genreIds: MutableList<Int>): Int {
            var genreId = 0
            for (i in genreIds) genreId = i
            return genreId
        }


        @TypeConverter
        @JvmStatic
        fun intToGenreIds(genrId: Int): List<Int>?{
            val list = mutableListOf<Int>()
            list.add(genrId)
            return list
        }

        @TypeConverter
        @JvmStatic
        fun originCountryToString(originCountry: MutableList<String>): String {
            var originCount = ""
            for (i in originCountry) originCount = i
            return originCount
        }


        @TypeConverter
        @JvmStatic
        fun StringToOriginCountry(originCountry: String): List<String>?{
            val list = mutableListOf<String>()
            list.add(originCountry)
            return list
        }
    }
}