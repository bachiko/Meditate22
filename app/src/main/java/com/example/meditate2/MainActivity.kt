package com.example.meditate2

import android.os.Bundle
import android.util.Log.d
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.meditate2.auth.LoginFragment
import com.example.meditate2.auth.PagerAdapter
import com.example.meditate2.auth.RegisterFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        val pagerAdapter =
            PagerAdapter(supportFragmentManager, 1)

        pagerAdapter.addFragment(LoginFragment())
        pagerAdapter.addFragment(RegisterFragment())

        viewPager.adapter = pagerAdapter
    }
}