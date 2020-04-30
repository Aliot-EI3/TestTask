package com.test.task.ui.detail

import android.app.WallpaperManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.squareup.picasso.Picasso
import com.test.task.R
import com.test.task.dagger.component.FragmentComponent
import com.test.task.extension.loadImageByUrl
import com.test.task.ui.base.BaseContract
import com.test.task.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.fragment_detail.view.*
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class DetailFragment : BaseFragment(), DetailContract.View {

    @Inject
    lateinit var presenter: DetailContract.Presenter

    override fun getLayoutId(): Int {
        return R.layout.fragment_detail
    }

    override fun injectPresenter(component: FragmentComponent): BaseContract.Presenter<BaseContract.View> {
        component.inject(this)
        return presenter as BaseContract.Presenter<BaseContract.View>
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var bitmapImage: Bitmap? = null
        arguments?.let {
            val safeArgs = DetailFragmentArgs.fromBundle(it)
            val image = safeArgs.imageExtra

            text_title.text = image.title

            loadImageByUrl(image.url).into(object :
                com.squareup.picasso.Target { // это можно в презентер вынести
                override fun onBitmapFailed(ex: Exception?, errorDrawable: Drawable?) {
                    Timber.e(ex)
                }

                override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                    bitmapImage = bitmap
                    view.image_view?.setImageBitmap(bitmap)
                }

                override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            })
        }

        wallpaper_button.setOnClickListener {

            val wallpaperManager = WallpaperManager.getInstance(context)
            try {
                bitmapImage?.let { wallpaperManager.setBitmap(bitmapImage) }
            } catch (ex: IOException) {
                Timber.e(ex)
            }
        }
    }

}