package com.guruthedev.instagram.di

import android.content.Context
import com.guruthedev.instagram.IgApplication
import dagger.Module

@Module
class AppModule {
    fun provideContext(app: IgApplication): Context {
        return app
    }
}