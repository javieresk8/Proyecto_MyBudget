package com.erazojavier.proyecto_mybudget.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.erazojavier.proyecto_mybudget.R
import com.erazojavier.proyecto_mybudget.models.Egreso
import com.erazojavier.proyecto_mybudget.models.Ingreso

class EgresoAdapter(val egresos:ArrayList<Egreso>) : RecyclerView.Adapter<EgresoAdapter.ViewHolder>() {

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        val nombreEgreso: TextView = view.findViewById(R.id.textViewNombreEgr)
        val descripcionEgreso: TextView = view.findViewById(R.id.textViewDescrEgr)
        val montoEgreso: TextView = view.findViewById(R.id.textViewMontoEgr)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val header = LayoutInflater.from(parent.context)
            .inflate(R.layout.egresos_list,parent,false)
        return ViewHolder(header)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nombreEgreso.text = egresos[position].nombreEgreso
        holder.descripcionEgreso.text = egresos[position].descripcion
        holder.montoEgreso.text = "$"+egresos[position].montoEgreso
    }
    override fun getItemCount() = egresos.size
}