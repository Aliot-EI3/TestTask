package com.test.task.ui.detail

import com.test.task.ui.base.BaseContract

class DetailContract : BaseContract() {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>

}