package com.example.tommycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity(), Calculator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


    }
    var text_result = findViewById<TextView>(R.id.text_result)
    override fun onDigit(view: View) {
        if (text_result.text.toString().trim() == "0") text_result.text = ""
        text_result.append((view as TextView).text.toString())
    }

    override fun onOperator(view: View) {
        text_result.append("${(view as TextView).text}")
    }

    override fun onClear(view: View) {
        text_result.text = "0"
    }

    override fun onEqual(view: View) {
        try {
            val expression = ExpressionBuilder(text_result.text.toString()).build()
            val result = expression.evaluate()
            text_result.text = (if (result % 1 > 0) result else
                result.toInt()).toString()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override fun onBracketStrat(view: View) {
        text_result.append("(")
    }

    override fun onvBracketEnd(view: View) {
        text_result.append(")")
    }

    override fun onDecimal(view: View) {
        text_result.append(".")
    }

    override fun onDelete(view: View) {
        val resultAfterDel :String = text_result.text.toString()
        if (resultAfterDel.isNotEmpty()){
            text_result.text = resultAfterDel.dropLast(n = 1 )
        }else{
            text_result.text="0"
        }
    }


}