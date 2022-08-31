package com.erazojavier.proyecto_mybudget.ingresos

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentIngresoNuevoBinding
import com.erazojavier.proyecto_mybudget.home.homeActivity
import com.erazojavier.proyecto_mybudget.models.CuentaBancaria
import com.erazojavier.proyecto_mybudget.models.Ingreso
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class IngresoNuevoFragment : Fragment() {

    private var _binding: FragmentIngresoNuevoBinding? = null
    private val db = Firebase.firestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = homeActivity.usuario
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIngresoNuevoBinding.inflate(inflater, container, false)
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonIngresoNuevoCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_nuevoIngresoFragment_to_resumenIngresoFragment)
        }

        binding.buttonNuevoIngr.setOnClickListener{
            //recuperar el nombre de usaurio (enviado desde el login)
            val nombreIngreso = binding.textInputNombreIngr.text.toString()
            val montoIngreso = binding.textInputMontoIngr.text.toString()
            val frecuencia = binding.textInputFrecIngr.text.toString()
            val descripcion = binding.textInputDescIngr.text.toString()
            val cuentaBancaria = binding.textInputCuentaIngr.text.toString()
            var ingresoNuevo = Ingreso(usuario, nombreIngreso, montoIngreso, cuentaBancaria, descripcion, frecuencia )
            //Guardar en firebase el ahorro
            crearNuevoIngreso(ingresoNuevo)
            findNavController().navigate(R.id.action_nuevoIngresoFragment_to_resumenIngresoFragment)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun crearNuevoIngreso(ingresoNuevo: Ingreso){

        db.collection("ingreso_usuario")
            .add(ingresoNuevo)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(activity, "Ingreso Correcto", Toast.LENGTH_LONG).show()
                acreditarValorCuenta(usuario, ingresoNuevo.cuentaBancaria, Integer.valueOf(ingresoNuevo.montoIngreso))

            }
            .addOnFailureListener { e ->
                Toast.makeText(activity, "ERROR: $e", Toast.LENGTH_LONG).show()
            }
    }

    private fun acreditarValorCuenta(usuario: String, numeroCta: String, monto: Int){

        var idCuenta = ""
        var cuentaTemporal = CuentaBancaria()
        db.collection("bancos_usuario")
            .whereEqualTo("numeroCuenta", numeroCta)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    idCuenta = document.id
                    cuentaTemporal  = document.toObject(CuentaBancaria::class.java)
                    var saldoFinal = (Integer.valueOf(cuentaTemporal.montoInicial)+monto)
                    cuentaTemporal.montoInicial = saldoFinal.toString()

                }

                //Mandamos a actualizar el valor
                db.collection("bancos_usuario")
                    .document(idCuenta)
                    .set(cuentaTemporal)
                    .addOnSuccessListener {
                        Toast.makeText(activity,"Saldo actualizado exitosamente", Toast.LENGTH_LONG).show()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(activity,"Error al actualizar la cuenta" , Toast.LENGTH_LONG).show()
                    }


            }
            .addOnFailureListener { e ->
                Toast.makeText(activity, "ERROR: $e", Toast.LENGTH_LONG).show()
            }
    }



}