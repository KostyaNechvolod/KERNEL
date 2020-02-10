package com.nechvolod.konstantin.kernelapp.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nechvolod.konstantin.kernelapp.data.model.TtnModel
import java.util.*


class GsonConverter {

    var gson = Gson()

    @TypeConverter
    fun stringToSomeObjectList(data: String?): List<TtnModel.Code> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<TtnModel.Code>>() {

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun someObjectListToString(someObjects: List<TtnModel.Code>): String {
        return gson.toJson(someObjects)
    }
}
