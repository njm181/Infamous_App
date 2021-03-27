package com.njm.infamous.data.entityDAO

import androidx.room.*
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.domain.entity.SeriesList
import com.njm.infamous.presentation.utils.Resource

@Dao
interface SerieDAO {

    @Query("SELECT * FROM result")
    suspend fun getAll(): List<Result>

    @Query("SELECT * FROM result WHERE id = (:serieId)")
    suspend fun findById(serieId: Int): Result

    @Query("SELECT * FROM result WHERE name LIKE :serieName")
    suspend fun findByName(serieName: String): Result

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(result: List<Result>)

    @Update
    suspend fun updateAll(result: List<Result>)

}