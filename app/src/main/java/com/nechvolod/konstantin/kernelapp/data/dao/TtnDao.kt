package com.nechvolod.konstantin.kernelapp.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel

@Dao
interface TtnDao {

    @Query("SELECT * FROM ttn")
    fun findAll(): LiveData<List<TtnModel>>

    @Query("SELECT * FROM ttn WHERE id == :id")
    fun findTtnById(id: Int): LiveData<TtnModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addList(ttnList: List<TtnModel>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addItem(ttn: TtnModel):Long

    @Query("UPDATE ttn SET codeList = :list WHERE id == :id")
    fun updateTtnSecList(list: List<TtnModel.Code>, id: Int)
}