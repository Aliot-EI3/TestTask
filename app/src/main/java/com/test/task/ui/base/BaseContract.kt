package com.test.task.ui.base

abstract class BaseContract {

    interface View

    interface Presenter<in V> {
        fun unsubscribe()
        fun attach(view: V)
    }

}