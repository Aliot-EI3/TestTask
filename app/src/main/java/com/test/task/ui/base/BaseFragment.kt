package com.test.task.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.task.BaseApp
import com.test.task.dagger.component.DaggerFragmentComponent
import com.test.task.dagger.component.FragmentComponent
import com.test.task.dagger.module.FragmentModule

abstract class BaseFragment : Fragment(), BaseContract.View {

    private var basePresenter: BaseContract.Presenter<BaseContract.View>? = null

    private var rootView: View? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (rootView == null) {
            basePresenter = injectPresenter(
                DaggerFragmentComponent
                    .builder()
                    .appComponent(BaseApp.instance.component)
                    .fragmentModule(FragmentModule())
                    .build()
            )
            rootView = inflater.inflate(getLayoutId(), container, false)
            basePresenter?.attach(this)
        }
        return rootView
    }

    abstract fun getLayoutId(): Int

    protected abstract fun injectPresenter(component: FragmentComponent): BaseContract.Presenter<BaseContract.View>

    override fun onDestroy() {
        super.onDestroy()
        basePresenter?.unsubscribe()
    }

}