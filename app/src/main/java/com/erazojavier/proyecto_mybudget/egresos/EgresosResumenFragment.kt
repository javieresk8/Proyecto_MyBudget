package com.erazojavier.proyecto_mybudget.egresos

import android.content.ContentValues
import android.content.Intent
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
import com.erazojavier.proyecto_mybudget.adapters.EgresoAdapter
import com.erazojavier.proyecto_mybudget.adapters.IngresosAdapter
import com.erazojavier.proyecto_mybudget.cuentas.cuentasActivity
import com.erazojavier.proyecto_mybudget.databinding.FragmentEgresosResumenBinding
import com.erazojavier.proyecto_mybudget.home.homeActivity
import com.erazojavier.proyecto_mybudget.models.Egreso
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EgresosResumenFragment : Fragment() {

    private var _binding: FragmentEgresosResumenBinding? = null
    private val db = Firebase.firestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = homeActivity.usuario
    var egresos = arrayListOf<Egreso>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEgresosResumenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obtenerEgresos(usuario)
        egresos.clear()
        binding.buttonNuevoEgreso.setOnClickListener {
            findNavController().navigate(R.id.action_egresosResumenFragment_to_egresoNuevoFragment)
        }

//        binding.buttonIrAlInicio.setOnClickListener{
//            val miIntent =  Intent(activity, homeActivity::class.java)
//            startActivity(miIntent)
//        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun obtenerEgresos(usuario: String){

        db.collection("egreso_usuario")
            .whereEqualTo("idUsuario", usuario)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val egreso = document.toObject(Egreso::class.java)
                    egresos.add(egreso)
                }

                var sumaEgresos = 0
                egresos.forEach { egreso ->
                    sumaEgresos +=egreso.montoEgreso.toInt()
                }
                binding.textViewMontoTotalEgresos.text = "$"+sumaEgresos.toString()
                val recyclerViewEgresos: RecyclerView = binding.recyclerViewEgresos
                recyclerViewEgresos.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = EgresoAdapter(egresos)
                }



            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}