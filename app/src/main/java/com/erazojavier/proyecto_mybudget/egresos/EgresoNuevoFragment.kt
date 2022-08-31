package com.erazojavier.proyecto_mybudget.egresos

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentEgresoNuevoBinding
import com.erazojavier.proyecto_mybudget.databinding.FragmentIngresoNuevoBinding
import com.erazojavier.proyecto_mybudget.models.CuentaBancaria
import com.erazojavier.proyecto_mybudget.models.Egreso
import com.erazojavier.proyecto_mybudget.models.Ingreso
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class EgresoNuevoFragment : Fragment() {

    private var _binding: FragmentEgresoNuevoBinding? = null
    private val db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = "javieresk8"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEgresoNuevoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonCancelNuevoEgr.setOnClickListener {
            findNavController().navigate(R.id.action_egresoNuevoFragment_to_egresosResumenFragment)
        }

        binding.buttonNuevoEgr.setOnClickListener{
            //recuperar el nombre de usaurio (enviado desde el login)
            val nombreEgreso = binding.textInputNombreEgr.text.toString()
            val montoEgreso = binding.textInputMontoEgr.text.toString()
            val frecuencia = binding.textInputFreqEgr.text.toString()
            val descripcion = binding.textInputDescrEgr.text.toString()
            val cuentaBancaria = binding.textInputCuentaEgr.text.toString()
            val esPlanificado = binding.switchEsPlanifEgr.isChecked
            var egresoNuevo = Egreso(usuario, nombreEgreso, montoEgreso, cuentaBancaria, descripcion,esPlanificado, frecuencia )
            //Guardar en firebase el ahorro
            crearNuevoEgreso(egresoNuevo)
            findNavController().navigate(R.id.action_egresoNuevoFragment_to_egresosResumenFragment)

        }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun crearNuevoEgreso(egresoNuevo: Egreso) {
        db.collection("egreso_usuario")
            .add(egresoNuevo)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(activity, "Ingreso Correcto", Toast.LENGTH_LONG).show()
                debitarValorCuenta(usuario, egresoNuevo.cuentaBancaria, Integer.valueOf(egresoNuevo.montoEgreso))

            }
            .addOnFailureListener { e ->
                Toast.makeText(activity, "ERROR: $e", Toast.LENGTH_LONG).show()
            }
    }

    private fun debitarValorCuenta(usuario: String, numeroCta: String, monto: Int){

        var idCuenta = ""
        var cuentaTemporal = CuentaBancaria()
        db.collection("bancos_usuario")
            .whereEqualTo("numeroCuenta", numeroCta)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                    idCuenta = document.id
                    cuentaTemporal  = document.toObject(CuentaBancaria::class.java)
                    var saldoFinal = (Integer.valueOf(cuentaTemporal.montoInicial)-monto)
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