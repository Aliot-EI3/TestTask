package com.test.task.ui.main

import com.nhaarman.mockito_kotlin.mock
import com.test.task.model.Image
import com.test.task.network.NetworkApi
import com.test.task.util.TestSchedulerProvider
import io.mockk.MockKAnnotations
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify


internal class MainPresenterTest {

    private val mockView: MainContract.View = mock()

    private val mockApi: NetworkApi = mock()

    private lateinit var presenter: MainPresenter

    private lateinit var testScheduler: TestScheduler

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        testScheduler = TestScheduler()
        presenter = MainPresenter(mockApi, TestSchedulerProvider(testScheduler))
        presenter.attach(mockView)
    }

    @Test
    fun loadData_success() {
        val images = listOf(Image(1, 1, "title", "url", "trUrl"))
        doReturn(Observable.just(images))
            .`when`(mockApi)
            .getImageList()
        presenter.loadData()

        testScheduler.triggerActions()

        verify(mockView).showProgress()
        verify(mockView).displayImages(images)
        verify(mockView).hideProgress()
    }

    @Test
    fun loadData_error() {
        val error: Throwable = mock()
        doReturn(Observable.just(error))
            .`when`(mockApi)
            .getImageList()

        presenter.loadData()

        testScheduler.triggerActions()

        verify(mockView).showProgress()
        verify(mockView).showErrorMessage()
        verify(mockView).hideProgress()
    }

    @After
    fun detach() {
        presenter.unsubscribe()
    }
}