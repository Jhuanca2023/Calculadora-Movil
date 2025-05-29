package com.example.mycalculadora

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnCalculadoraNormal = findViewById<Button>(R.id.btnCalculadoraNormal)
        val btnCalculadoraCientifica = findViewById<Button>(R.id.btnCalculadoraCientifica)

        btnCalculadoraNormal.setOnClickListener {
            startActivity(Intent(this, CalculadoraNormalActivity::class.java))
        }

        btnCalculadoraCientifica.setOnClickListener {
            startActivity(Intent(this, CalculadoraCientificaActivity::class.java))
        }
    }
}