package com.nechvolod.konstantin.kernelapp.base.cache

import android.content.SharedPreferences
import com.google.gson.Gson
import java.lang.reflect.Type

class BaseStorageSource<T>(
    private val mPreferences: SharedPreferences,
    private val KEY: String,
    private val mType: Type
) :
    CacheSource<T> {

    override var lastCacheUpdateTimeMillis: Long
        get() = mPreferences.getLong("$KEY time", 0)
        set(currentMillis) = mPreferences.edit().putLong("$KEY time", currentMillis).apply()

    override fun contains(): Boolean {
        return mPreferences.contains(KEY)
    }

    override fun remove() {
        mPreferences.edit().remove(KEY).apply()
    }

    override fun put(model: T) {
        val json = Gson().toJson(model, mType)
        mPreferences.edit().putString(KEY, json).apply()
    }

    override fun get(): T {
        return Gson().fromJson(mPreferences.getString(KEY, ""), mType)
    }
}