package com.example.mycalculadora

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CalculadoraNormalActivity : AppCompatActivity() {
    private lateinit var pantalla: TextView
    private lateinit var operacion: TextView
    private var primerNumero = 0.0
    private var operador = ""
    private var nuevoNumero = true
    private var operacionCompleta = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculadora_normal)

        pantalla = findViewById(R.id.tvPantalla)
        operacion = findViewById(R.id.tvOperacion)

        // Configurar listeners para botones numéricos
        val botonesNumericos = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in botonesNumericos) {
            findViewById<Button>(id).setOnClickListener { numeroPresionado(it as Button) }
        }

        // Configurar listeners para operaciones
        findViewById<Button>(R.id.btnSuma).setOnClickListener { operacionPresionada("+") }
        findViewById<Button>(R.id.btnResta).setOnClickListener { operacionPresionada("-") }
        findViewById<Button>(R.id.btnMultiplicacion).setOnClickListener { operacionPresionada("×") }
        findViewById<Button>(R.id.btnDivision).setOnClickListener { operacionPresionada("÷") }
        findViewById<Button>(R.id.btnIgual).setOnClickListener { calcularResultado() }
        findViewById<Button>(R.id.btnLimpiar).setOnClickListener { limpiarPantalla() }
        findViewById<Button>(R.id.btnPunto).setOnClickListener { agregarPunto() }
        findViewById<Button>(R.id.btnBorrar).setOnClickListener { borrarUltimo() }
    }

    private fun numeroPresionado(boton: Button) {
        val numeroPresionado = boton.text.toString()
        if (nuevoNumero) {
            pantalla.text = numeroPresionado
            nuevoNumero = false
        } else {
            if (pantalla.text == "0") {
                pantalla.text = numeroPresionado
            } else {
                pantalla.append(numeroPresionado)
            }
        }
        operacionCompleta = operacion.text.toString() + numeroPresionado
        operacion.text = operacionCompleta
    }

    private fun operacionPresionada(op: String) {
        try {
            primerNumero = pantalla.text.toString().toDouble()
            operador = op
            nuevoNumero = true
            operacionCompleta = pantalla.text.toString() + " " + op + " "
            operacion.text = operacionCompleta
        } catch (e: Exception) {
            limpiarPantalla()
        }
    }

    private fun calcularResultado() {
        try {
            val segundoNumero = pantalla.text.toString().toDouble()
            val resultado = when (operador) {
                "+" -> primerNumero + segundoNumero
                "-" -> primerNumero - segundoNumero
                "×" -> primerNumero * segundoNumero
                "÷" -> primerNumero / segundoNumero
                else -> segundoNumero
            }
            
            operacionCompleta += " = "
            operacion.text = operacionCompleta
            
            pantalla.text = if (resultado.toLong().toDouble() == resultado) {
                resultado.toLong().toString()
            } else {
                String.format("%.2f", resultado)
            }
            nuevoNumero = true
        } catch (e: Exception) {
            pantalla.text = "Error"
            operacion.text = ""
            nuevoNumero = true
        }
    }

    private fun limpiarPantalla() {
        pantalla.text = "0"
        operacion.text = ""
        primerNumero = 0.0
        operador = ""
        nuevoNumero = true
        operacionCompleta = ""
    }

    private fun agregarPunto() {
        if (nuevoNumero) {
            pantalla.text = "0."
            operacionCompleta += "0."
            nuevoNumero = false
        } else if (!pantalla.text.contains(".")) {
            pantalla.append(".")
            operacionCompleta += "."
        }
        operacion.text = operacionCompleta
    }

    private fun borrarUltimo() {
        val textoActual = pantalla.text.toString()
        if (textoActual.length > 1) {
            pantalla.text = textoActual.substring(0, textoActual.length - 1)
            if (operacionCompleta.isNotEmpty()) {
                operacionCompleta = operacionCompleta.substring(0, operacionCompleta.length - 1)
                operacion.text = operacionCompleta
            }
        } else {
            pantalla.text = "0"
            if (operacionCompleta.isNotEmpty()) {
                operacionCompleta = operacionCompleta.substring(0, operacionCompleta.length - 1)
                if (operacionCompleta.isEmpty()) operacionCompleta = "0"
                operacion.text = operacionCompleta
            }
            nuevoNumero = true
        }
    }
} 