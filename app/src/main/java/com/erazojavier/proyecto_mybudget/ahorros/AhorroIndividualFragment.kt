package com.erazojavier.proyecto_mybudget.ahorros

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentAhorroIndividualBinding
import com.erazojavier.proyecto_mybudget.databinding.FragmentAhorroResumenBinding


/**
 * A simple [Fragment] subclass.
 * Use the [AhorroIndividualFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AhorroIndividualFragment : Fragment() {
    private var _binding: FragmentAhorroIndividualBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAhorroIndividualBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        binding.buttonNuevoAhorro.setOnClickListener {
//            findNavController().navigate(R.id.action_resumenAhorrosFragment_to_nuevoAhorroFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}