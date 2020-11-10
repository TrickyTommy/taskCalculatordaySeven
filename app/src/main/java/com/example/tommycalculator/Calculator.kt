package com.example.tommycalculator

import android.view.View

interface Calculator {

    fun onDigit(view: View)
    fun onOperator(view: View)
    fun onClear(view: View)
    fun onEqual(view: View)
    fun onBracketStrat(view: View)
    fun onvBracketEnd(view: View)
    fun onDecimal(view: View)
    fun onDelete(view: View)
}