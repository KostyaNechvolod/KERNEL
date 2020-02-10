package com.nechvolod.konstantin.kernelapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nechvolod.konstantin.kernelapp.data.dao.TtnDao
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import com.nechvolod.konstantin.kernelapp.utils.GsonConverter

@Database(entities = [TtnModel::class], version = 1, exportSchema = false)
@TypeConverters(GsonConverter::class)
abstract class TtnDataBase : RoomDatabase() {
    abstract val userDao: TtnDao
}