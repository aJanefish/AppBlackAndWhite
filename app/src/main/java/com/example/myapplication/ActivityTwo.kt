package com.example.myapplication

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_two.*

/**
 * 对单个ImageView黑白化
 */
class ActivityTwo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_two)

        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_two_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }
}