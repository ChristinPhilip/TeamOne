package com.example.kotlintv

import android.view.animation.Interpolator

internal class MyBounceInterpolator(amplitude: Double, frequency: Int) :
    Interpolator {
    private var mAmplitude = 1.0
    private var mFrequency = 10.0
    override fun getInterpolation(time: Float): Float {
        return (-1 * Math.pow(Math.E, -time / mAmplitude) *
                Math.cos(mFrequency * time) + 1).toFloat()
    }

    init {
        mAmplitude = amplitude
        mFrequency = frequency.toDouble()
    }
}