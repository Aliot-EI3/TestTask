package com.test.task.ui.about

import com.test.task.ui.base.BaseContract

class AboutContract : BaseContract() {

    interface View : BaseContract.View

    interface Presenter : BaseContract.Presenter<View>
}