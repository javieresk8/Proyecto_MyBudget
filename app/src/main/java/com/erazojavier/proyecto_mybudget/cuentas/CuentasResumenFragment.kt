package com.erazojavier.proyecto_mybudget.cuentas

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.adapters.CuentaBancariaAdapter
import com.erazojavier.proyecto_mybudget.adapters.EgresoAdapter
import com.erazojavier.proyecto_mybudget.databinding.FragmentCuentasResumenBinding
import com.erazojavier.proyecto_mybudget.models.CuentaBancaria
import com.erazojavier.proyecto_mybudget.models.Egreso
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

//import com.erazojavier.proyecto_mybudget.cuentas.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class CuentasResumenFragment : Fragment() {

    private var _binding: FragmentCuentasResumenBinding? = null
    private val db = Firebase.firestore

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = "javieresk8"
    var cuentas = arrayListOf<CuentaBancaria>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCuentasResumenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obtenerCuentas(usuario)
        binding.buttonNuevaCuenta.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
    }

    private fun obtenerCuentas(usuario: String) {
        db.collection("bancos_usuario")
            .whereEqualTo("idUsuario", usuario)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val cuenta = document.toObject(CuentaBancaria::class.java)
                    println("La cuenta que llega es"+cuenta.nombreBanco )
                    cuentas.add(cuenta)
                }

                val recyclerViewEgresos: RecyclerView = binding.recyclerViewCuentas
                recyclerViewEgresos.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = CuentaBancariaAdapter(cuentas)
                }



            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}