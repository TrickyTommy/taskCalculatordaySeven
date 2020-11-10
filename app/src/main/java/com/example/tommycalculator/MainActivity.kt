package com.example.tommycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun appendOnExpresentation(string: String, canClear : Boolean){
        if(canClear){
            tvResult.text=""
        }
    }
}