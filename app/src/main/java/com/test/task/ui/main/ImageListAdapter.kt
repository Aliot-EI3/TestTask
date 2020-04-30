package com.test.task.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.test.task.R
import com.test.task.extension.setImageResize
import com.test.task.model.Image
import com.test.task.ui.base.BaseFragment
import kotlinx.android.synthetic.main.image_item.view.*

class ImageListAdapter(
    private val c: Context,
    private val images: List<Image>,
    fragment: BaseFragment
) :
    RecyclerView.Adapter<ImageListAdapter.ImageViewHolder>() {

    private val listener: OnItemClickListener

    init {
        this.listener = fragment as OnItemClickListener
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(c).inflate(R.layout.image_item, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = images[position]
        holder.itemImageView.setImageResize(image.url)
        holder.itemImageView.setOnClickListener {
            listener.itemClick(image)
        }
    }

    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemImageView = view.item_image_view as ImageView
    }

    interface OnItemClickListener {
        fun itemClick(image: Image)
    }

}