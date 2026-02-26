package com.example.atividade01_desenvolvimentomobileii

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextGas = findViewById<EditText>(R.id.gas_input)
        val editTextAlcohol = findViewById<EditText>(R.id.alcohol_input)
        val calculateButton = findViewById<Button>(R.id.calculate_btn_id)
        val priceResultText = findViewById<TextView>(R.id.priceResult)

        calculateButton.setOnClickListener {
            val textGas = editTextGas.text.toString()
            val priceGas = textGas.toDoubleOrNull()

            val textAlcohol = editTextAlcohol.text.toString()
            val priceAlcohol = textAlcohol.toDoubleOrNull()


            val areValuesNull = priceGas == null || priceAlcohol == null

            if (areValuesNull) {
                priceResultText.text = "Preencha todos os campos"
                return@setOnClickListener
            }

            val areValuesNegative = (priceAlcohol <= 0) || (priceGas <= 0)

            if(areValuesNegative) {
                priceResultText.text = "Preços devem ser maiores que zero"
                return@setOnClickListener
            }

            val result = priceAlcohol / priceGas



            if (result < 0.7) {
                priceResultText.text = "ETANOL é mais vantajoso"
            } else if(result >= 0.7) {
                priceResultText.text = "GASOLINA é mais vantajoso"
            }
        }
    }
}