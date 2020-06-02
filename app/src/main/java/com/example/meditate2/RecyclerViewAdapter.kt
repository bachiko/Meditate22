package com.example.meditate2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.photo_layout.view.*


class RecyclerViewAdapter(
    private val photos: List<PhotoModel>,
    private val activity: PhotosActivity
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun getItemCount() = photos.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photo_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        Glide.with(activity).load(photo.url).into(holder.image)
        holder.image.setOnClickListener {
            val intent = Intent(activity, SinglePhotoActivity::class.java)
            intent.putExtra("image", photo.url)
            activity.startActivity(intent)
        }

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.myImage
    }
}