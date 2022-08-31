package com.erazojavier.proyecto_mybudget.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.models.Ingreso

class IngresosAdapter(val ingresos:ArrayList<Ingreso>) : RecyclerView.Adapter<IngresosAdapter.ViewHolder>(){
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nombreIngreso: TextView = view.findViewById(R.id.textViewNombreIngreso)
        val descripcionIngreso: TextView = view.findViewById(R.id.textViewDescIngreso)
        val montoIngreso: TextView = view.findViewById(R.id.textViewMontoIngreso)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val header = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingresos_list,parent,false)
        return ViewHolder(header)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombreIngreso.text = ingresos[position].nombreIngreso
        holder.descripcionIngreso.text = ingresos[position].descripcion
        holder.montoIngreso.text = "$"+ingresos[position].montoIngreso
    }
    override fun getItemCount() = ingresos.size



}