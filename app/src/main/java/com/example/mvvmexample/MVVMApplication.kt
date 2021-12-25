package com.example.mvvmexample

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.mvvmexample.data.db.AppDatabase
import com.example.mvvmexample.data.network.MyAPI
import com.example.mvvmexample.data.network.NetworkConnectionInterceptor
import com.example.mvvmexample.data.repositories.UserRepository
import com.example.mvvmexample.ui.auth.AuthViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware{
    @RequiresApi(Build.VERSION_CODES.M)
    override val kodein = Kodein.lazy {
        import(androidXModule(this@MVVMApplication))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyAPI(instance()) }
        bind() from singleton { AppDatabase(instance()) }
        bind() from singleton { UserRepository(instance(), instance()) }
        bind() from provider { AuthViewModelFactory(instance()) }
    }
}