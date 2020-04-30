package com.test.task.ui.main

import com.test.task.model.Image
import com.test.task.ui.base.BaseContract

class MainContract : BaseContract() {

    interface View : BaseContract.View {
        fun showProgress()
        fun hideProgress()
        fun displayImages(images: List<Image>)
        fun showDetailFragment(image: Image)
        fun showErrorMessage()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onItemClick(image: Image)
        fun loadData()
    }
}