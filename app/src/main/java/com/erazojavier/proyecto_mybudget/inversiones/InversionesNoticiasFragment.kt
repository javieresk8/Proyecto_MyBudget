package com.erazojavier.proyecto_mybudget.inversiones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.FragmentInversionesNoticiasBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class InversionesNoticiasFragment : Fragment() {

    private var _binding: FragmentInversionesNoticiasBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInversionesNoticiasBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardViewNoticia1.setOnClickListener {
            findNavController().navigate(R.id.action_inversionesNoticiasFragment_to_inversionNoticiaIndFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}