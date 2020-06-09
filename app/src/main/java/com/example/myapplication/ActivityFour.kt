package com.example.myapplication

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_four.*

/**
 * 黑白化研究
 */
class ActivityFour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
        init()
    }

    private fun init() {
        val mPaint = Paint()
        val cm = ColorMatrix()
        //cm.setSaturation(0F)
        //cm.setRotate(0,50F)
        cm.setRotate(1,50F)
        //cm.setRotate(2,50F)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }
}