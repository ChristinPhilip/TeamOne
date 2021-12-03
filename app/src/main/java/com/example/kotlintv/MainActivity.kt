package com.example.kotlintv

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.FragmentActivity


class MainActivity : FragmentActivity(),View.OnClickListener {
    private lateinit var mHome: TextView
    private lateinit var mMovie: TextView
    private lateinit var mSports: TextView
    private lateinit var mChannels: TextView
    private lateinit var header: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        if (savedInstanceState == null) {
            openFragment("Home")
        }
        setFocusListener()
    }

    private fun setFocusListener() {
        mHome.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mHome.setTextColor(Color.BLACK)
                mHome.startAnimation(
                    AnimationUtils.loadAnimation(
                        applicationContext,
                        android.R.anim.slide_in_left
                    )
                )
            } else {
                mHome.setTextColor(Color.WHITE)
            }
        }

        mMovie.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mMovie.startAnimation(
                    AnimationUtils.loadAnimation(
                        applicationContext,
                        android.R.anim.slide_in_left
                    )
                )
                mMovie.setTextColor(Color.BLACK)
            } else {
                mMovie.setTextColor(Color.WHITE)
            }
        }

        mSports.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mSports.startAnimation(
                    AnimationUtils.loadAnimation(
                        applicationContext,
                        android.R.anim.slide_in_left
                    )
                )
                mSports.setTextColor(Color.BLACK)
            } else {
                mSports.setTextColor(Color.WHITE)
            }
        }

        mChannels.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mChannels.startAnimation(
                    AnimationUtils.loadAnimation(
                        applicationContext,
                        android.R.anim.slide_in_left
                    )
                )
                mChannels.setTextColor(Color.BLACK)
            } else {
                mChannels.setTextColor(Color.WHITE)
            }
        }
    }

    private fun init() {
        mHome = findViewById(R.id.home_tv)
        mMovie = findViewById(R.id.movies_tv)
        mSports = findViewById(R.id.sports_tv)
        mChannels = findViewById(R.id.channels_tv)
        header = findViewById(R.id.header)
        mHome.setOnClickListener(this)
        mMovie.setOnClickListener(this)
        mSports.setOnClickListener(this)
        mChannels.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.home_tv->{
                openFragment("Home")
            }
            R.id.movies_tv->{
                openFragment("Movies")
            }
            R.id.sports_tv->{
                openFragment("Sports")
            }
            R.id.channels_tv->{
                openFragment("Channels")
            }

        }
    }

    private fun openFragment(page: String) {
        header.text = page
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_browse_fragment, RowFragment(page))
            .commit()
    }
}