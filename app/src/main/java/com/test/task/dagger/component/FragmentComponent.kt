package com.test.task.dagger.component

import com.test.task.dagger.module.FragmentModule
import com.test.task.dagger.scope.FragmentScope
import com.test.task.ui.about.AboutFragment
import com.test.task.ui.detail.DetailFragment
import com.test.task.ui.main.MainFragment
import dagger.Component

@FragmentScope
@Component(dependencies = [AppComponent::class], modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(mainFragment: MainFragment)

    fun inject(detailFragment: DetailFragment)

    fun inject(aboutFragment: AboutFragment)

}