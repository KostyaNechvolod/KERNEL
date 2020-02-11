package com.nechvolod.konstantin.kernelapp.data.repositories

import androidx.lifecycle.LiveData
import com.nechvolod.konstantin.kernelapp.data.dao.TtnDao
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TtnRepository( private val ttnDao: TtnDao) {

    val data = ttnDao.findAll()

    suspend fun getTtnById(id: Int): LiveData<TtnModel>{
        return withContext(Dispatchers.IO){
            ttnDao.findTtnById(id)
        }
    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val list: ArrayList<TtnModel> = arrayListOf()
            for (i in 1..10) {
                list.add(
                    TtnModel(
                        id = i,
                        ttnNumber = "123456${i}",
                        ttnDate = "${i}.${i + 2}.${i+2000}",
                        trailerPlate = "1234567",
                        trackPlate = "7654321",
                        senderName = "sender${i}",
                        driverName = "driver${i + 1}",
                        codeList = listOf(TtnModel.Code(codeNumber = "12345", isGood = true))
                    )
                )
            }
            ttnDao.add(list)
        }
    }
}