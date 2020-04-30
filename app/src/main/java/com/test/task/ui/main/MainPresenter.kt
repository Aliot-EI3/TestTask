package com.test.task.ui.main

import com.test.task.model.Image
import com.test.task.network.NetworkApi
import com.test.task.ui.base.BasePresenter
import com.test.task.util.SchedulerProvider
import timber.log.Timber
import javax.inject.Inject

class MainPresenter @Inject constructor(
    private var api: NetworkApi,
    private var scheduler: SchedulerProvider
) :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    override fun onItemClick(image: Image) {
        view?.showDetailFragment(image)
        Timber.d(image.toString())
    }

    override fun loadData() {
        view?.showProgress()
        subscriptions.add(
            api.getImageList()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.ui())
                .doFinally { view?.hideProgress() }
                .subscribe({ list: List<Image>? ->
                    list?.let { view?.displayImages(it) }
                }, { ex ->
                    view?.showErrorMessage()
                    Timber.e(ex)
                })
        )
    }
}