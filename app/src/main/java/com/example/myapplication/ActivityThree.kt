package com.example.myapplication

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_three.*

/**
 * 对activity_three_tv_two进行黑白化
 */
class ActivityThree : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_three)


        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_three_tv_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }
}