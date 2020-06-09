package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

/**
 * 整个Activity黑白化
 */
class ActivityOne : AppCompatActivity() {
    private val KEY_TYPE = "KEY_TYPE"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one)
        if (getSP().getBoolean(KEY_TYPE, true)) {
            val mPaint = Paint()
            val cm = ColorMatrix()
            cm.setSaturation(0F)
            mPaint.colorFilter = ColorMatrixColorFilter(cm)
            window.decorView.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
        }
    }

    private fun getSP(): SharedPreferences {
        return getSharedPreferences("zy", Context.MODE_PRIVATE)
    }
}