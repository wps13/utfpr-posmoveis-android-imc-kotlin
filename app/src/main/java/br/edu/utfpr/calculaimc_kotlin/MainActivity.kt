package br.edu.utfpr.calculaimc_kotlin

import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.pow

class MainActivity : AppCompatActivity() {
    private lateinit var etPeso: EditText
    private lateinit var etAltura: EditText
    private lateinit var tvResultado: TextView
    private lateinit var btCalcular: Button
    private lateinit var btLimpar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etPeso = findViewById(R.id.etPeso)
        etAltura = findViewById(R.id.etAltura)
        tvResultado = findViewById(R.id.tvResultado)
        btCalcular = findViewById(R.id.btCalcular)
        btLimpar = findViewById(R.id.btLimpar)

        btCalcular.setOnClickListener {
            btnCalcularOnClick()
        }

        btLimpar.setOnClickListener {
            btnLimparOnClick()
        }

        btLimpar.setOnLongClickListener {
            Toast.makeText(this, "Botão de limpar", Toast.LENGTH_SHORT).show()
            false
        }
    }

    private fun btnLimparOnClick() {
        etPeso.setText("")
        etAltura.setText("")
        tvResultado.text = "0.0"
        etPeso.requestFocus()
    }

    private fun btnCalcularOnClick() {
        if(etPeso.text.toString().isEmpty()){
            etPeso.error = "Campo peso deve ser informado"
            return
        }

        if(etAltura.text.toString().isEmpty()){
            etAltura.error = "Campo altura deve ser informado"
            return
        }
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc = peso / altura.pow(2)

        // versao java alike
        // val df = DecimalFormat("0.0")
        // tvResultado.text = df.format(imc)

        // kotlin way
        tvResultado.text = "%.2f".format(imc)
    }

}