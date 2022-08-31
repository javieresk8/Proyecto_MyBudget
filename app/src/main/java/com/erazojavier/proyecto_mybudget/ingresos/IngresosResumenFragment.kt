package com.erazojavier.proyecto_mybudget.ingresos

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
import com.erazojavier.proyecto_mybudget.adapters.IngresosAdapter
import com.erazojavier.proyecto_mybudget.databinding.FragmentIngresosResumenBinding
import com.erazojavier.proyecto_mybudget.home.homeActivity
import com.erazojavier.proyecto_mybudget.models.Ingreso
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class IngresosResumenFragment : Fragment() {

    private var _binding: FragmentIngresosResumenBinding? = null
    private val db = Firebase.firestore
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val usuario = homeActivity.usuario

    var ingresos = arrayListOf<Ingreso>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentIngresosResumenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        obtenerIngresos(usuario)

        ingresos.clear()
        binding.buttonNuevoIngreso.setOnClickListener {
            findNavController().navigate(R.id.action_resumenIngresosFragment_to_nuevoIngresoFragment)
        }
//        binding.buttonIrAlInicioIng.setOnClickListener{
//            val miIntent =  Intent(activity, homeActivity::class.java)
//            startActivity(miIntent)
//        }




    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun obtenerIngresos(usuario: String){

        db.collection("ingreso_usuario")
            .whereEqualTo("idUsuario", usuario)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val ingreso = document.toObject(Ingreso::class.java)
                    println(ingreso.nombreIngreso)
                    ingresos.add(ingreso)
                    println(ingresos[0].nombreIngreso)

                }

                //TODO: No se esta llenando los registros
                var sumaIngresos = 0
                ingresos.forEach { ingreso ->
                    println("El valor en foreach" + ingreso.montoIngreso)

                    sumaIngresos +=ingreso.montoIngreso.toInt()
                }
                //println("El valor en foreach" + ingresos[0].montoIngreso)
                binding.textViewMontoTotalEgresos.text = "$"+sumaIngresos.toString()
                val recyclerViewIngresos: RecyclerView = binding.reciclerViewIngresos
                recyclerViewIngresos.apply {
                    layoutManager = LinearLayoutManager(activity)
                    adapter = IngresosAdapter(ingresos)
                }



            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents: ", exception)
            }
    }
}