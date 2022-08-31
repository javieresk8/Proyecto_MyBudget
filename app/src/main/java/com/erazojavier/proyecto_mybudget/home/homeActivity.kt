package com.erazojavier.proyecto_mybudget.home

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.databinding.ActivityHomeBinding
//import com.erazojavier.proyecto_mybudget.home.databinding.ActivityHomeBinding

class homeActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    companion object {
        var usuario = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var usuario = this.getIntent().getExtras()?.getString("email")
        println("El usuario que recibo es $usuario")
        homeActivity.usuario = usuario.toString()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_home)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_home)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}