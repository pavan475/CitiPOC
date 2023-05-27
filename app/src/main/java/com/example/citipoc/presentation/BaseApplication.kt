package com.example.citipoc.presentation

import android.app.Application
import com.example.citipoc.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
     //   DaggerAppComponent.builder().application(this).build()

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    /* lateinit var applicationComponent: AppComponent

     override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
         return DaggerAppComponent.builder().application(this).build()
     }*/
}