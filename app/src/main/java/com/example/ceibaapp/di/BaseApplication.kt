package com.example.ceibaapp.di

import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

open class BaseApplication : DaggerApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().application(this).build()
    }

}