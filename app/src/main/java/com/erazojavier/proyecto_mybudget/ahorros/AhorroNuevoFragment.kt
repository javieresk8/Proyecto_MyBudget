package com.erazojavier.proyecto_mybudget.ahorros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentAhorroNuevoBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AhorroNuevoFragment : Fragment() {

    private var _binding: FragmentAhorroNuevoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAhorroNuevoBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonNuevoAhorroCancelar.setOnClickListener {
            findNavController().navigate(R.id.action_nuevoAhorroFragment_to_resumenAhorrosFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}