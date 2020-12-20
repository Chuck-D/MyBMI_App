package com.kcappmaker.mybmiapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calculate_button.setOnClickListener {
            // Check if no view has focus: In this case, gets rid of virtual keypad.
            val view = this.currentFocus
            view?.let { v ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(v.windowToken, 0)
            }

            var decimalListWeight = mutableListOf<String>()
            var decimalListHeight = mutableListOf<String>()

            for (x in weight_edit_text.text){
                if(x == '.'){
                    decimalListWeight.add(".")
                }
            }
            for (x in height_edit_text.text){
                if(x == '.'){
                    decimalListHeight.add(".")
                }
            }
            if (decimalListWeight.size > 1 || decimalListHeight.size > 1){
                Toast.makeText(this,"Only one decimal allowed", Toast.LENGTH_SHORT).show()
            }
            else {

                if (weight_edit_text.text.isNotEmpty() && height_edit_text.text.isNotEmpty()) {
                    calculateBMI()
                } else {
                    Toast.makeText(this, "enter the required field", Toast.LENGTH_SHORT).show()
                }
            }
    }
    }

    private fun calculateBMI(){
        val weight = weight_edit_text.text.toString().toFloat()
        val height = height_edit_text.text.toString().toFloat()

        val myBMI = (weight / (height * height)) * 703

        bmi_text_view.text = String.format("Your BMI is %.2f", myBMI)

        if (myBMI < 18.5) {
            bmi_image_view.setImageResource(R.drawable.underweight)
        } else if (myBMI >= 18.5 && myBMI < 24.9) {
            bmi_image_view.setImageResource(R.drawable.healthy)
        } else if (myBMI >= 24.9 && myBMI < 29.9) {
            bmi_image_view.setImageResource(R.drawable.overweight)
        } else {
            bmi_image_view.setImageResource(R.drawable.obese)
        }

    }


}