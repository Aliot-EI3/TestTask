package com.test.task.ui.about


import com.test.task.R
import com.test.task.dagger.component.FragmentComponent
import com.test.task.ui.base.BaseContract
import com.test.task.ui.base.BaseFragment
import javax.inject.Inject

class AboutFragment : BaseFragment(), AboutContract.View {

    @Inject
    lateinit var presenter: AboutContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_about
    }

    override fun injectPresenter(component: FragmentComponent): BaseContract.Presenter<BaseContract.View> {
        component.inject(this)
        return presenter as BaseContract.Presenter<BaseContract.View>
    }
}