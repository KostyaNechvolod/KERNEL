package com.nechvolod.konstantin.kernelapp.data.repositories

import androidx.lifecycle.LiveData
import com.nechvolod.konstantin.kernelapp.data.dao.TtnDao
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class TtnRepository( private val ttnDao: TtnDao) {

    val data = ttnDao.findAll()

    suspend fun getTtnById(id: Int): LiveData<TtnModel> {

        return runBlocking {
            async { ttnDao.findTtnById(id)}.await()
        }
    }

    suspend fun refresh() {
        withContext(Dispatchers.IO) {
            val list: ArrayList<TtnModel> = arrayListOf()
            for (i in 1..10) {
                list.add(
                    TtnModel(
                        ttnNumber = "123456${i}",
                        ttnDate = "${i}.${i + 2}.${i+2000}",
                        trailerPlate = "1234567",
                        trackPlate = "7654321",
                        senderName = "Sender${i}",
                        driverName = "Driver${i + 1}",
                        codeList = listOf(TtnModel.Code(codeNumber = "12345", isGood = true))
                    )
                )
            }
            ttnDao.addList(list)
        }
    }

    suspend fun addItem(ttn: TtnModel): Long{
        return withContext(Dispatchers.IO){
            ttnDao.addItem(ttn)
        }
    }

    suspend fun updateSecList(list: List<TtnModel.Code>, id: Int){
        withContext(Dispatchers.IO){
            ttnDao.updateTtnSecList(list, id)
        }
    }
}