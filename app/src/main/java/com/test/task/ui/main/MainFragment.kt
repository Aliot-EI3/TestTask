package com.test.task.ui.main

import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.recyclerview.widget.GridLayoutManager
import com.test.task.R
import com.test.task.dagger.component.FragmentComponent
import com.test.task.model.Image
import com.test.task.ui.base.BaseContract
import com.test.task.ui.base.BaseFragment
import com.test.task.util.Constants.Companion.GRID_SPAN_COUNT
import kotlinx.android.synthetic.main.fragment_main.*
import javax.inject.Inject

class MainFragment : BaseFragment(), MainContract.View, ImageListAdapter.OnItemClickListener {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun injectPresenter(component: FragmentComponent): BaseContract.Presenter<BaseContract.View> {
        component.inject(this)
        return presenter as BaseContract.Presenter<BaseContract.View>
    }

    override fun onResume() {
        super.onResume()
        presenter.loadData()
    }

    override fun showProgress() {
        progressBar?.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar?.visibility = View.GONE
    }

    override fun displayImages(images: List<Image>) {
        context.let {
            val adapter = ImageListAdapter(requireContext(), images, this)
            recyclerView!!.layoutManager = GridLayoutManager(context, GRID_SPAN_COUNT)
            recyclerView!!.adapter = adapter
        }
    }

    override fun showDetailFragment(image: Image) {
        (activity as MainActivity).showDetailFragment(image)
    }

    override fun showErrorMessage() {
        Toast.makeText(context, R.string.load_error_message, LENGTH_SHORT).show()
    }

    override fun itemClick(image: Image) {
        presenter.onItemClick(image)
    }

}