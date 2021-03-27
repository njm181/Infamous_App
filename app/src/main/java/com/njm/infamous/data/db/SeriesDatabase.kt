package com.njm.infamous.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.njm.infamous.data.entityDAO.SerieDAO
import com.njm.infamous.domain.entity.Result
import com.njm.infamous.domain.util.DBConverters

@Database(entities = [Result::class], version = 1)
@TypeConverters(DBConverters::class)
abstract class SeriesDatabase: RoomDatabase() {

    abstract fun serieDao(): SerieDAO

    companion object {
        private val DB_NAME = "series.db"
        private var instance: SeriesDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): SeriesDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, SeriesDatabase::class.java,
                    DB_NAME)
                    .fallbackToDestructiveMigration()
                    .build()

            return instance!!

        }
    }


}