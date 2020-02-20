package com.nechvolod.konstantin.kernelapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.nechvolod.konstantin.kernelapp.utils.GsonConverter

@Entity(tableName = "ttn")
data class TtnModel(
    @PrimaryKey(autoGenerate = true) var id: Int?= null,
    val ttnNumber: String,
    val ttnDate: String,
    val trailerPlate: String,
    val trackPlate: String,
    val driverName: String,
    val senderName: String,
    @TypeConverters(GsonConverter::class)
    val codeList: List<Code>
) {
    data class Code(
        var codeNumber: String,
        var isGood: Boolean = false
    )
}