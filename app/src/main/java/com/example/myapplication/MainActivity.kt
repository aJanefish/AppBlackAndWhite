package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    fun testOne(view: View) {
        startActivity(Intent(this, ActivityOne::class.java))
    }

    fun testTwo(view: View) {
        startActivity(Intent(this, ActivityTwo::class.java))
    }
    fun testThree(view: View) {
        startActivity(Intent(this, ActivityThree::class.java))
    }

    fun testFour(view: View) {
        startActivity(Intent(this, ActivityFour::class.java))
    }
}