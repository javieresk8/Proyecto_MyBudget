package com.erazojavier.proyecto_mybudget.ingresos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentIngresosResumenBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class IngresosResumenFragment : Fragment() {

    private var _binding: FragmentIngresosResumenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentIngresosResumenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNuevoIngreso.setOnClickListener {
            findNavController().navigate(R.id.action_resumenIngresosFragment_to_nuevoIngresoFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}