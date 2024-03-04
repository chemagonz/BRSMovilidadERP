package com.advantys.brsmovilidaderp.UI.Views.Centros

import Centro_ViewModel
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Centro
import com.advantys.brsmovilidaderp.R


class Centros_Adapter(private val centrosList: List<Centro?>, private val centroViewModel: Centro_ViewModel): RecyclerView.Adapter<Centros_ViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Centros_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Centros_ViewHolder(layoutInflater.inflate(R.layout.item_centros, parent, false))
    }

    override fun getItemCount(): Int = centrosList.size

    override fun onBindViewHolder(holder: Centros_ViewHolder, position: Int) {
        holder.render(centrosList[position])
        val item = centrosList[position]
        holder.nombreCentro.text= item?.nombre
        holder.nCentro.text= item?.centro.toString()

       /* holder.verDetalles.setOnClickListener {
            if (item != null) {
                centroViewModel.btnDetalle(item)
            }
        }*/
    }
}
    class Centros_ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val nombreCentro= view.findViewById<TextView>(R.id.nombreCentro)
        val nCentro= view.findViewById<TextView>(R.id.NCENTRO)
        val verDetalles= view.findViewById<ImageButton>(R.id.verDetallesButton)

        fun render(centrosModel: Centro?){
            nombreCentro.text= centrosModel?.nombre
            nCentro.text= centrosModel?.centro.toString()
        }

    }