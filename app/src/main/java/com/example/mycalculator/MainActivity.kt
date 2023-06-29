package com.example.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private var tvInput: TextView? = null

    var lastNumeric: Boolean = false //checking if the last entry is numeric
    var lastDot: Boolean = false //checking if last entry is a dot


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput = findViewById(R.id.tvInput)
    }


    fun onDigit(view: View) {
        tvInput?.append((view as Button).text)
        lastNumeric = true //if we don't add this, lastnumeric will be always false
        lastDot = false
    }

    fun onClear(view: View) {
        tvInput?.text = ""
    }

    fun onEqual(view: View) {

        if (lastNumeric) {
            var tvValue = tvInput?.text.toString()
            var prefix = ""

            try {
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1) //if we have "-99" it will be "99" after this
                }
                if (tvValue.contains("-")){
                    val splitValue = tvValue.split("-")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() - two.toDouble()).toString())
                }else if (tvValue.contains("+")){
                    val splitValue = tvValue.split("+")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() + two.toDouble()).toString())
                }else if (tvValue.contains("/")){
                    val splitValue = tvValue.split("/")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() / two.toDouble()).toString())
                }
                else if (tvValue.contains("*")){
                    val splitValue = tvValue.split("*")

                    var one = splitValue[0] //99
                    var two = splitValue[1] //1

                    if(prefix.isNotEmpty()){
                        one = prefix + one
                    }

                    tvInput?.text = removeZero((one.toDouble() * two.toDouble()).toString())

                }


            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }


    private fun removeZero(result: String) : String{
        var value = result
        if(result.contains(".0")){
            value = result.substring(0, result.length-2)
        }
        return value
    }

    fun onDecimalPoint(view: View) {
        //there shouldn't be two or more dots next to eachother
        if (lastNumeric && !lastDot) {
            tvInput?.append(".")
            lastNumeric = false
            lastDot = true
        }


    }

    fun onOperator(view: View) {
        tvInput?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                tvInput?.append((view as Button).text)
                lastNumeric = false // Update the flag
                lastDot = false    // Reset the DOT flag
            }
        }
    }


        private fun isOperatorAdded(value: String): Boolean {
            return if (value.startsWith("-")) {
                false
            } else {
                value.contains("//") || value.contains("*") || value.contains("+") ||
                        value.contains("-")
            }
        }


    }