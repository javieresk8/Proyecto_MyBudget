package com.erazojavier.proyecto_mybudget.egresos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentEgresoNuevoBinding
import com.erazojavier.proyecto_mybudget.databinding.FragmentIngresoNuevoBinding
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

            }
            .addOnFailureListener { e ->
                Toast.makeText(activity, "ERROR: $e", Toast.LENGTH_LONG).show()
            }
    }
}