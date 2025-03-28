package br.edu.utfpr.calculaimc_kotlin

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale
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
            Toast.makeText(this, getString(R.string.toast_bt_limpar), Toast.LENGTH_SHORT).show()
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
        if (etPeso.text.toString().isEmpty()) {
            etPeso.error = getString(R.string.error_peso)
            return
        }

        if (etAltura.text.toString().isEmpty()) {
            etAltura.error = getString(R.string.error_altura)
            return
        }
        val peso = etPeso.text.toString().toDouble()
        val altura = etAltura.text.toString().toDouble()

        val imc: Double

        if (Locale.getDefault().language.equals("en")) {
            imc = 703 * (peso / altura.pow(2))
            val nf = NumberFormat.getNumberInstance(Locale.US)
            val df = nf as DecimalFormat
            tvResultado.text = df.format(imc)
        } else {
            imc = peso / altura.pow(2)
            val df = DecimalFormat("0.0")
            tvResultado.text = df.format(imc)
        }
    }

}