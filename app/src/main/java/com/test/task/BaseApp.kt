package com.test.task

import android.app.Application
import com.test.task.dagger.component.AppComponent
import com.test.task.dagger.component.DaggerAppComponent
import com.test.task.dagger.module.AppModule
import timber.log.Timber
import timber.log.Timber.DebugTree

class BaseApp : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        initDagger()
        initTimber()

    }

    private fun initDagger() {
        component = DaggerAppComponent.builder()
            .appModule(AppModule(this)).build()
        component.inject(this)
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}