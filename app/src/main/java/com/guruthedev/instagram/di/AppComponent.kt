package com.guruthedev.instagram.di

import com.guruthedev.instagram.IgApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@Component(modules = [AndroidInjectionModule::class,FragmentModule::class,AppModule::class,ViewModelModule::class,AdapterModule::class])
interface AppComponent : AndroidInjector<IgApplication> {
    @Component.Factory
    interface Factory {
        fun createApp(@BindsInstance app: IgApplication): AppComponent
    }
}
