package com.example.tommycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception

class MainActivity : AppCompatActivity(), Calculator {
    lateinit var txtInput: TextView
    lateinit var txthasil: TextView
    var lastNumeric: Boolean = false
    var stateError: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtInput = findViewById(R.id.tvExpression)
        txthasil = findViewById(R.id.tvResult)

    }
//    var text_result = findViewById<TextView>(R.id.text_result)
//    var tvExpression = findViewById<TextView>(R.id.tvExpression)
    override fun onDigit(view: View) {
    if (stateError) {
        // If current state is Error, replace the error message
        txtInput.text = (view as TextView).text
        stateError = false
    } else {
        // If not, already there is a valid expression so append to it
        txtInput.append((view as TextView).text)
    }
    // Set the flag
    lastNumeric = true
}



    override fun onOperator(view: View) {
        if (lastNumeric && !stateError) {
            txtInput.append((view as TextView).text)
            lastNumeric = false
            lastDot = false    // Reset the DOT flag
        }
    }

    override fun onClear(view: View) {
        this.txtInput.text = ""
        this.txthasil.text = ""
        lastNumeric = false
        stateError = false
        lastDot = false
    }

    override fun onEqual(view: View) {
        // If the current state is error, nothing to do.
        // If the last input is a number only, solution can be found.
        if (lastNumeric && !stateError) {
            // Read the expression
            val txt = txtInput.text.toString()
            // Create an Expression (A class from exp4j library)
            val expression = ExpressionBuilder(txt).build()
            try {
                // Calculate the result and display
                val result = expression.evaluate()
                txthasil.text = result.toString()
                lastDot = true // Result contains a dot
            } catch (ex: ArithmeticException) {
                // Display an error message
                txtInput.text = "Error"
                stateError = true
                lastNumeric = false
            }
        }
    }

    override fun onBracketStrat(view: View) {
        txtInput.append("(")
    }

    override fun onvBracketEnd(view: View) {
        txtInput.append(")")
    }

    override fun onDecimal(view: View) {
        txtInput.append(".")
    }

    override fun onDelete(view: View) {
        val resultAfterDel :String = txtInput.text.toString()
        if (resultAfterDel.isNotEmpty()){
            txtInput.text = resultAfterDel.dropLast(n = 1 )
        }else{
            txtInput.text="0"
        }
    }




}