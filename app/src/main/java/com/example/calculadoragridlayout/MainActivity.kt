package com.example.calculadoragridlayout

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.calculadoragridlayout.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var txtResult: TextView
    lateinit var binding: ActivityMainBinding
    var operacionPendiente = false
    var suma = false
    var resta = false
    var numAnterior = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        txtResult = binding.textView  // findViewById
    }

    fun presionoBoton(v: View){

        val btnPresiono = v as TextView
        if(txtResult.text.equals("0") || operacionPendiente) {
            txtResult.text = btnPresiono.text
            operacionPendiente=false
        }
        else
            txtResult.text = "${txtResult.text}${btnPresiono.text}"

    }

    fun ejecutarOperacion(){
        var numeroActual = txtResult.text.toString().toInt()
        var result = 0
        if(suma)
            result = numeroActual + numAnterior
        else if(resta)
            result = numAnterior - numeroActual
        txtResult.text = result.toString()
    }

    fun presionoOperacion(v: View){

        val btnPresiono = v as TextView
        if(suma||resta)
            ejecutarOperacion()
        operacionPendiente=true
        when(btnPresiono.text){
            "+"->{
               suma = true
               resta = false
               numAnterior = txtResult.text.toString().toInt()
            }
            "-"->{
                suma = false

                resta = true
                numAnterior = txtResult.text.toString().toInt()
            }
            "="->{
                suma = false

                resta = false
                numAnterior = 0
            }
            "C"->{
                suma = false
                resta = false
                txtResult.text="0"
                numAnterior = 0
            }
        }

    }
}