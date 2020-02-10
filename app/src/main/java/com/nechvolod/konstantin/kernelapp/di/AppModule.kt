package com.nechvolod.konstantin.kernelapp.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.google.gson.reflect.TypeToken
import com.nechvolod.konstantin.kernelapp.BuildConfig
import com.nechvolod.konstantin.kernelapp.base.cache.BaseCache
import com.nechvolod.konstantin.kernelapp.base.cache.BaseStorageSource
import com.nechvolod.konstantin.kernelapp.data.dao.TtnDao
import com.nechvolod.konstantin.kernelapp.data.db.TtnDataBase
import com.nechvolod.konstantin.kernelapp.data.entities.AuthCredentials
import com.nechvolod.konstantin.kernelapp.data.repositories.TtnRepository
import com.nechvolod.konstantin.kernelapp.ui.activity.auth.AuthVM
import com.nechvolod.konstantin.kernelapp.ui.activity.main.MainVM
import com.nechvolod.konstantin.kernelapp.ui.activity.splash.SplashVM
import com.nechvolod.konstantin.kernelapp.ui.fragment.home.HomeVM
import com.nechvolod.konstantin.kernelapp.ui.fragment.sign_in.SignInVM
import com.nechvolod.konstantin.kernelapp.ui.fragment.ttn_deatails.TtnDetailsVM
import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single<SharedPreferences> {
        androidApplication().getSharedPreferences(
            BuildConfig.APPLICATION_ID,
            Context.MODE_PRIVATE
        )
    }

    single<BaseCache<AuthCredentials>>(named("auth cred")) {
        val cacheSource = BaseStorageSource<AuthCredentials>(
            get(), AuthCredentials::class.java.toString(),
            object : TypeToken<AuthCredentials>() {}.type
        )
        object : BaseCache<AuthCredentials>(cacheSource) {}
    }


    fun provideDatabase(application: Application): TtnDataBase {
        return Room.databaseBuilder(application, TtnDataBase::class.java, "kernel.database")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }


    fun provideDao(database: TtnDataBase): TtnDao {
        return database.userDao
    }

    fun provideTtnRepository(dao: TtnDao): TtnRepository {
        return TtnRepository(dao)
    }

    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    single { provideTtnRepository(get()) }

    viewModel { SplashVM(get(named("auth cred"))) }
    viewModel { AuthVM() }
    viewModel { MainVM() }
    viewModel { HomeVM( get()) }
    viewModel { SignInVM() }
    viewModel { TtnDetailsVM() }
}