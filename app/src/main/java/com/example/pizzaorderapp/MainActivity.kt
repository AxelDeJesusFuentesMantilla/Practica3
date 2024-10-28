package com.example.pizzaorderapp

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referencias a los elementos
        val spinnerPizzaType = findViewById<Spinner>(R.id.spinnerPizzaType)
        val radioGroupSize = findViewById<RadioGroup>(R.id.radioGroupSize)
        val checkBoxExtraCheese = findViewById<CheckBox>(R.id.checkBoxExtraCheese)
        val checkBoxExtraFries = findViewById<CheckBox>(R.id.checkBoxExtraFries)
        val checkBoxExtraPineapple = findViewById<CheckBox>(R.id.checkBoxExtraPineapple)
        val checkBoxExtraOnions = findViewById<CheckBox>(R.id.checkBoxExtraOnions)
        val buttonConfirm = findViewById<Button>(R.id.buttonConfirm)
        val textViewSummary = findViewById<TextView>(R.id.textViewSummary)

        // Configura el botón de confirmación
        buttonConfirm.setOnClickListener {
            val selectedPizzaType = spinnerPizzaType.selectedItem.toString()

            // Verificar si se ha seleccionado el tipo de pizza
            if (selectedPizzaType.isEmpty()) {
                Toast.makeText(this, "Por favor, elija el tipo de pizza.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Verificar si se ha seleccionado el tamaño de pizza
            val selectedSize = when (radioGroupSize.checkedRadioButtonId) {
                R.id.radioIndividual -> "Individual"
                R.id.radioMedium -> "Mediana"
                R.id.radioLarge -> "Grande"
                R.id.radioFamily -> "Familiar"
                else -> ""
            }

            if (selectedSize.isEmpty()) {
                Toast.makeText(this, "Por favor, elija el tamaño de la pizza.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Obtiene las selecciones de los ingredientes adicionales
            val extras = mutableListOf<String>()
            if (checkBoxExtraCheese.isChecked) extras.add("Extra queso")
            if (checkBoxExtraFries.isChecked) extras.add("Extra papas")
            if (checkBoxExtraPineapple.isChecked) extras.add("Extra piña")
            if (checkBoxExtraOnions.isChecked) extras.add("Extra cebollas")

            // Genera el resumen
            val summary = "Tipo: $selectedPizzaType\n" +
                    "Tamaño: $selectedSize\n" +
                    "Extras: ${extras.joinToString(", ")}"
            textViewSummary.text = summary

            // Mostrar un Toast de confirmación
            Toast.makeText(this, "Pedido confirmado: $selectedPizzaType - $selectedSize", Toast.LENGTH_LONG).show()
        }

    }
}
