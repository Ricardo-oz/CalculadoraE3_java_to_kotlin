package com.example.calculadorae3

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

// Este código en Kotlin fue convertido de un APP en Java, las diferencias son:
//
// 1. Propiedades no nulas:** Las variables como `num1`, `num2`, y demás botones pueden ser declaradas sin necesidad de que sean `null`
//    utilizando `lateinit var`, ya que serán inicializadas más tarde con `findViewById`, eliminando la necesidad de comprobaciones de null.
//    En lugar de `var num1: EditText? = null`, sería mejor usar `lateinit var num1: EditText`.
//
// 2. Listeners más concisos:** Kotlin permite escribir los listeners de una manera más directa. En lugar de `View.OnClickListener { v: View? -> ... }`
//    podemos simplemente usar la lambda `setOnClickListener { ... }`, lo que simplifica la sintaxis.
//
// 3. Null Safety:** Las funciones como `validateInput()` pueden beneficiarse del sistema de null safety. Al hacer que `num1` y `num2` no sean nulos,
//    evitamos la necesidad de usar operadores de anulación (`!!`) y posibles excepciones `NullPointerException`.
//
// 4. Interoperabilidad con Java:** A pesar de que este código es Kotlin, sigue siendo 100% interoperable con Java, por lo que puede coexistir con
//    clases Java en un proyecto Android.
// 5. No es neserio colocar ; al final de cada linea
//
// 6. El codigo es mas corto
//
// 7. La conversion dio errores y el codigo tubo que ser corregido

class MainActivity : AppCompatActivity() {
    private lateinit var num1: EditText
    private lateinit var num2: EditText
    private lateinit var result: TextView
    private lateinit var add: Button
    private lateinit var subtract: Button
    private lateinit var multiply: Button
    private lateinit var divide: Button
    private lateinit var equals: Button
    private lateinit var exit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicialización de vistas usando lateinit
        num1 = findViewById(R.id.num1)
        num2 = findViewById(R.id.num2)
        result = findViewById(R.id.result)
        add = findViewById(R.id.button_add)
        subtract = findViewById(R.id.button_subtract)
        multiply = findViewById(R.id.button_multiply)
        divide = findViewById(R.id.button_divide)
        equals = findViewById(R.id.button_equals)
        exit = findViewById(R.id.button_exit)

        // Operaciones con lambdas más concisas
        add.setOnClickListener { calculate("+") }
        subtract.setOnClickListener { calculate("-") }
        multiply.setOnClickListener { calculate("x") }
        divide.setOnClickListener { calculate("/") }

        // Botón igual
        equals.setOnClickListener {
            if (validateInput()) {
                Toast.makeText(this, "Seleccione una operación", Toast.LENGTH_SHORT).show()
            }
        }

        // Botón para salir
        exit.setOnClickListener { finish() }
    }

    // Validación más clara sin uso de nulls
    private fun validateInput(): Boolean {
        if (num1.text.toString().isEmpty() || num2.text.toString().isEmpty()) {
            Toast.makeText(this, "Por favor ingrese ambos números", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    // Método para realizar la operación
    private fun calculate(operator: String) {
        if (!validateInput()) return

        val n1 = num1.text.toString().toInt()
        val n2 = num2.text.toString().toInt()
        var res = 0

        when (operator) {
            "+" -> res = n1 + n2
            "-" -> res = n1 - n2
            "x" -> res = n1 * n2
            "/" -> {
                if (n2 == 0) {
                    Toast.makeText(this, "No se puede dividir por cero", Toast.LENGTH_SHORT).show()
                    return
                }
                res = n1 / n2
            }
        }
        result.text = "Resultado: $res"
    }
}