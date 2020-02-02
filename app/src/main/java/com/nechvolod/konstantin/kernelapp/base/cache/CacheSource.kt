package com.nechvolod.konstantin.kernelapp.base.cache

interface CacheSource<T> {
    var lastCacheUpdateTimeMillis: Long

    fun contains(): Boolean

    fun remove()

    fun put(model: T)

    fun get(): T
}

abstract class BaseCache<T>(
    protected var sp: CacheSource<T>,
    private val cacheExpirationTime: Long = 3000000
) {

    val isCached: Boolean
        get() = sp.contains()

    open val isExpired: Boolean
        get() {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = this.lastCacheUpdateTimeMillis

            val expired = currentTime - lastUpdateTime > cacheExpirationTime

            if (expired) {
                this.evictAll()
            }

            return expired
        }

    protected val lastCacheUpdateTimeMillis: Long
        get() = sp.lastCacheUpdateTimeMillis

    fun evictAll() {
        sp.remove()
    }

    protected fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        sp.lastCacheUpdateTimeMillis = currentMillis
    }


    open fun put(model: T): T {
        setLastCacheUpdateTimeMillis()
        sp.put(model)
        return model
    }

    open fun get(): T {
        return sp.get()
    }
}
