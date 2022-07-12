package com.erazojavier.proyecto_mybudget.egresos

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentEgresosResumenBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class EgresosResumenFragment : Fragment() {

    private var _binding: FragmentEgresosResumenBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEgresosResumenBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonNuevoEgreso.setOnClickListener {
            findNavController().navigate(R.id.action_egresosResumenFragment_to_egresoNuevoFragment)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}