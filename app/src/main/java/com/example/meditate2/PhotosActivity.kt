package com.example.meditate2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_photos.*
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException

class PhotosActivity : AppCompatActivity() {

    private lateinit var adapter: RecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_photos)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://www.mocky.io/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(ApiService::class.java)

        api.fetchPhotos().enqueue(object : Callback<List<PhotoModel>> {
            override fun onResponse(
                call: Call<List<PhotoModel>>,
                response: Response<List<PhotoModel>>
            ) {
                d("bacho", "onResponse ${response.body()!![0].url}")
                showData(response.body()!!)
            }

            override fun onFailure(call: Call<List<PhotoModel>>, t: Throwable) {
                d("bacho", "onFailure")
            }
        })
    }

    private fun showData(bands: List<PhotoModel>) {
        recyclerView.apply {
            layoutManager = GridLayoutManager(this@PhotosActivity, 2)
            adapter = RecyclerViewAdapter(bands, this@PhotosActivity)
            (adapter as RecyclerViewAdapter).notifyDataSetChanged()
        }
    }
}

