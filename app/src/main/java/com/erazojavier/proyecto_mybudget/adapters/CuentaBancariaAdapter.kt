package com.erazojavier.proyecto_mybudget.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.models.CuentaBancaria
import com.erazojavier.proyecto_mybudget.models.Egreso

class CuentaBancariaAdapter(val cuentas:ArrayList<CuentaBancaria>) : RecyclerView.Adapter<CuentaBancariaAdapter.ViewHolder>() {
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nombreBanco: TextView = view.findViewById(R.id.textViewBanco)
        val numeroCta: TextView = view.findViewById(R.id.textViewNumeroCta)
        val montoCta: TextView = view.findViewById(R.id.textViewValorCta)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val header = LayoutInflater.from(parent.context)
            .inflate(R.layout.cuentas_list,parent,false)
        return ViewHolder(header)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombreBanco.text = cuentas[position].nombreBanco
        holder.numeroCta.text = "Nro. Cta:"+cuentas[position].numeroCuenta
        holder.montoCta.text = "$"+cuentas[position].montoInicial
    }
    override fun getItemCount() = cuentas.size
}