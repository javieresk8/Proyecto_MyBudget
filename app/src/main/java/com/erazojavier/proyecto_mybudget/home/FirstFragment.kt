package com.erazojavier.proyecto_mybudget.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.ahorros.AhorrosActivity
import com.erazojavier.proyecto_mybudget.cuentas.CuentasResumenFragment
import com.erazojavier.proyecto_mybudget.cuentas.cuentasActivity
import com.erazojavier.proyecto_mybudget.databinding.FragmentHomeBinding
import com.erazojavier.proyecto_mybudget.egresos.EgresosActivity
import com.erazojavier.proyecto_mybudget.ingresos.IngresosActivity
import com.erazojavier.proyecto_mybudget.inversiones.InversionesActivity
import com.erazojavier.proyecto_mybudget.perfil.perfilActivity

//import com.erazojavier.proyecto_mybudget.home.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.botonCuentas.setOnClickListener {
            val miIntent =  Intent(activity, cuentasActivity::class.java)
            startActivity(miIntent)
        }


        binding.botonHomeIngresos.setOnClickListener {
            val miIntent =  Intent(activity, IngresosActivity::class.java)
            startActivity(miIntent)
        }

        binding.botonEgresos.setOnClickListener {
            val miIntent =  Intent(activity, EgresosActivity::class.java)
            startActivity(miIntent)
        }

        binding.botonAhorros.setOnClickListener {
            val miIntent =  Intent(activity, AhorrosActivity::class.java)
            startActivity(miIntent)
        }

        binding.botonPerfil.setOnClickListener {
            val miIntent =  Intent(activity, perfilActivity::class.java)
            startActivity(miIntent)
        }

        binding.botonInversiones.setOnClickListener {
            val miIntent =  Intent(activity, InversionesActivity::class.java)
            startActivity(miIntent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}