package edu.iest.androidwear

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.androidwear.databinding.ActivityRelojBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRelojBinding
    private var numeroUno: Int? = null
    private var numeroDos: Int? = null
    private var numeroMayor: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelojBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSaludo.text = "App Números"
        binding.bnCambio.text = "Verificar"

        // para poner la informacion junto con una vista base en el spinner.
        // intermediario que agarra informacion.
        val adaptador_sp1 = ArrayAdapter.createFromResource(this,
            R.array.misOpciones, android.R.layout.simple_spinner_item)
        adaptador_sp1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val adaptador_sp2 = ArrayAdapter.createFromResource(this,
            R.array.misOpciones, android.R.layout.simple_spinner_item)
        adaptador_sp2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.spNumberOne.adapter = adaptador_sp1
        binding.spNumberOne.onItemSelectedListener = this

        binding.spNumberTwo.adapter = adaptador_sp2
        binding.spNumberTwo.onItemSelectedListener = this

        binding.bnCambio.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
            if(numeroUno!! > numeroDos!!) {
                numeroMayor=numeroUno
            }else{
                numeroMayor=numeroDos
            }
            alerta.setTitle("Atención")
                .setMessage("El número mayor es $numeroMayor")
                .setCancelable(false)
                .setPositiveButton(
                    "Regresar",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        Toast.makeText(this, "Hasta luego!", Toast.LENGTH_SHORT).show()
                    })
                .show()
        }
    }

    override fun onItemSelected(vistaPadre: AdapterView<*>?, VistaRow: View?, posicion: Int, idVista: Long) {
        numeroUno = Integer.valueOf(vistaPadre?.getItemAtPosition(posicion).toString())
        numeroDos = Integer.valueOf(vistaPadre?.getItemAtPosition(posicion).toString())
    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}