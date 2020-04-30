package com.test.task.dagger.module

import com.test.task.dagger.scope.FragmentScope
import com.test.task.network.NetworkApi
import com.test.task.ui.about.AboutContract
import com.test.task.ui.about.AboutPresenter
import com.test.task.ui.detail.DetailContract
import com.test.task.ui.detail.DetailPresenter
import com.test.task.ui.main.MainContract
import com.test.task.ui.main.MainPresenter
import com.test.task.util.AppSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    @FragmentScope
    fun provideMainPresenter(
        api: NetworkApi,
        scheduler: AppSchedulerProvider
    ): MainContract.Presenter {
        return MainPresenter(api, scheduler)
    }

    @Provides
    @FragmentScope
    fun provideDetailPresenter(): DetailContract.Presenter {
        return DetailPresenter()
    }

    @Provides
    @FragmentScope
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }

}