package com.example.ceibaapp.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidSupportInjectionModule::class,
                AppModule::class,
                ViewModelFactoryModule::class,
                ActivityBuilderModule::class,
                UtilClassBuilderModule::class]
)
interface AppComponent : AndroidInjector<BaseApplication>{

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): AppComponent
    }
}