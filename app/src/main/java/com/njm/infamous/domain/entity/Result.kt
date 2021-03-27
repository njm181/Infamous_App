package com.njm.infamous.domain.entity

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Result(
    @ColumnInfo
    val backdrop_path: String,
    @ColumnInfo
    val first_air_date: String,
    @ColumnInfo
    val genre_ids: List<Int>,
    @PrimaryKey
    val id: Int,
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val origin_country: List<String>,
    @ColumnInfo
    val original_language: String,
    @ColumnInfo
    val original_name: String,
    @ColumnInfo
    val overview: String,
    @ColumnInfo
    val popularity: Double,
    @ColumnInfo
    val poster_path: String,
    @ColumnInfo
    val vote_average: Double,
    @ColumnInfo
    val vote_count: Int
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        TODO("genre_ids"),
        parcel.readInt(),
        parcel.readString()!!,
        parcel.createStringArrayList()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readString()!!,
        parcel.readDouble(),
        parcel.readInt()
    ) {
    }

    override fun describeContents(): Int {
        TODO("Not yet implemented")
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("Not yet implemented")
    }

    companion object CREATOR : Parcelable.Creator<Result> {
        override fun createFromParcel(parcel: Parcel): Result {
            return Result(parcel)
        }

        override fun newArray(size: Int): Array<Result?> {
            return arrayOfNulls(size)
        }
    }
}