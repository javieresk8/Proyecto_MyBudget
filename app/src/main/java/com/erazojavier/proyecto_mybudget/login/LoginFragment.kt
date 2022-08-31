package com.erazojavier.proyecto_mybudget.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.cuentas.cuentasActivity
import com.erazojavier.proyecto_mybudget.databinding.FragmentLoginBinding
import com.erazojavier.proyecto_mybudget.home.homeActivity

//import com.erazojavier.proyecto_mybudget.login.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonLogin2.setOnClickListener {
            val miIntent =  Intent(activity, homeActivity::class.java)
            startActivity(miIntent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}