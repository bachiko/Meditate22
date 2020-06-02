package com.example.meditate2


import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Environment
import android.os.Environment.DIRECTORY_PICTURES
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.GenericTransitionOptions.with
import com.bumptech.glide.Glide
import com.bumptech.glide.Glide.with
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_single_photo.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.util.*


class SinglePhotoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_single_photo)

        val image = intent.getStringExtra("image")
        Glide.with(this).load(image).into(singleImage)

        val intent = intent
        val urlFull = intent.getStringExtra("url_large")
        val id = intent.getStringExtra("id")
        val imageDetailViewModel = ImageDetailViewModel(applicationContext)

        /*val uri= FileProvider.getUriForFile(this@SinglePhotoActivity,BuildConfig.APPLICATION_ID +".fileprovider",
            File("${Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES)}/MeditateWallpapers/$id.jpg")
        )*/

        FileProvider.getUriForFile(
            Objects.requireNonNull(applicationContext),
            BuildConfig.APPLICATION_ID + ".provider", File("${Environment.getExternalStoragePublicDirectory(DIRECTORY_PICTURES)}/MeditateWallpapers/$id.jpg"))

        saveImage.setOnClickListener {
            with(this)
                .asBitmap()
                .load(urlFull)
                .into(object : CustomTarget<Bitmap>() {
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        CoroutineScope(Dispatchers.IO).launch {
                            imageDetailViewModel.downloadImage(resource, id!!)
                        }
                    }
                    override fun onLoadCleared(placeholder: Drawable?) {  }
                })
        }
    }
}