package com.test.task.ui.base

import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    private var weakReferenceView: WeakReference<V>? = null
    protected val subscriptions = CompositeDisposable()

    protected val view: V?
        get() = weakReferenceView?.get()

    private val isViewAttached: Boolean
        get() = weakReferenceView != null && weakReferenceView!!.get() != null

    override fun unsubscribe() {
        weakReferenceView?.clear()
        weakReferenceView = null
        subscriptions.clear()
    }

    override fun attach(view: V) {
        if (!isViewAttached) {
            weakReferenceView = WeakReference(view)
        }
    }

}