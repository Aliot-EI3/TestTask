package com.test.task.dagger.module

import com.test.task.BaseApp
import com.test.task.util.AppSchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BaseApp) {

    @Provides
    @Singleton
    fun provideApplication() = application

    @Provides
    @Singleton
    fun provideSchedulerProvider() = AppSchedulerProvider()
}