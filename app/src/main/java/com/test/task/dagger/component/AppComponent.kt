package com.test.task.dagger.component

import com.test.task.BaseApp
import com.test.task.dagger.module.AppModule
import com.test.task.dagger.module.NetworkModule
import com.test.task.dagger.module.RetrofitModule
import com.test.task.network.NetworkApi
import com.test.task.util.AppSchedulerProvider
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RetrofitModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(application: BaseApp)

    fun application(): BaseApp
    fun retrofit(): Retrofit
    fun networkApi(): NetworkApi
    fun schedulerProvider(): AppSchedulerProvider
}