package com.example.myapplication

import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_four.*

/**
 * 黑白化研究
 */
class ActivityFour : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_four)
    }

    //测试黑白化
    fun testOne1(view: View) {
        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setSaturation(0F)
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }

    fun testOne2(view: View) {
        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setYUV2RGB()
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }

    fun testOne3(view: View) {
        val mPaint = Paint()
        val cm = ColorMatrix()
        cm.setRGB2YUV()
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }

    fun testOne4(view: View) {
        val mPaint = Paint()
        val cm = ColorMatrix()
        val array: FloatArray = cm.array
        array[0] = -1F
        array[3] = 1F
        array[4] = 1F
        array[6] = -1F
        array[8] = 1F
        array[9] = 1F
        array[12] = -1F
        array[13] = 1F
        array[14] = 1F
        array[18] = 1F
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }

    fun testOne5(view: View) {
        val mPaint = Paint()
        val cm = ColorMatrix()
        val array: FloatArray = cm.array
        array[0]= 1.5F
        array[1]= 1.5F
        array[2]= 1.5F
        array[4]= -1F
        array[5]= 1.5F
        array[6]= 1.5F
        array[7]= 1.5F
        array[9]= -1F
        array[10]= 1.5F
        array[11]= 1.5F
        array[12]= 1.5F
        array[14]= -1F
        array[18]= 1F
        mPaint.colorFilter = ColorMatrixColorFilter(cm)
        activity_four_im_two.setLayerType(View.LAYER_TYPE_SOFTWARE, mPaint)
    }
}