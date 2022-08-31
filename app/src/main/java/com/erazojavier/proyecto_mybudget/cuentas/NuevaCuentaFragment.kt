package com.erazojavier.proyecto_mybudget.cuentas

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentCuentaNuevaBinding
import com.erazojavier.proyecto_mybudget.models.CuentaBancaria
import com.erazojavier.proyecto_mybudget.models.Egreso
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//import com.erazojavier.proyecto_mybudget.cuentas.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NuevaCuentaFragment : Fragment() {

    private var _binding: FragmentCuentaNuevaBinding? = null
    private val db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = "javieresk8"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCuentaNuevaBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNuevaCuentaCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        binding.buttonCrearCuenta.setOnClickListener{
            //recuperar el nombre de usaurio (enviado desde el login)
            val nombreCta = binding.textInputNombreCta.text.toString()
            val montoCta = binding.textInputMontoCta.text.toString()
            val tipoCta = binding.textInputTipoCta.text.toString()
            val numeroCta = binding.textInputNumeroCta.text.toString()
            var ctaNueva = CuentaBancaria(usuario, nombreCta, montoCta, numeroCta, tipoCta )
            //Guardar en firebase el ahorro
            crearNuevaCuenta(ctaNueva)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)

        }

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun crearNuevaCuenta(ctaNueva: CuentaBancaria) {
        db.collection("bancos_usuario")
            .add(ctaNueva)
            .addOnSuccessListener { documentReference ->
                Toast.makeText(activity, "Ingreso Correcto", Toast.LENGTH_LONG).show()

            }
            .addOnFailureListener { e ->
                Toast.makeText(activity, "ERROR: $e", Toast.LENGTH_LONG).show()
            }
    }
}
