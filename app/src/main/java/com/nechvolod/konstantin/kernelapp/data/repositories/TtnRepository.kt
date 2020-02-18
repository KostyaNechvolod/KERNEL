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
        /*lateinit var returnData: LiveData<TtnModel>
        withContext(Dispatchers.IO){
            val data = withContext(Dispatchers.Default) {
                ttnDao.findTtnById(id)
            }
            returnData = data.await()
        }
        return returnData*/
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
                        senderName = "sender${i}",
                        driverName = "driver${i + 1}",
                        codeList = listOf(TtnModel.Code(codeNumber = "12345", isGood = true))
                    )
                )
            }
            ttnDao.addList(list)
        }
    }

    suspend fun addItem(ttn: TtnModel){
        withContext(Dispatchers.IO){
            ttnDao.addItem(ttn)
        }
    }
}