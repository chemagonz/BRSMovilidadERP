package com.advantys.brsmovilidaderp.UI.Views.Centros

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.Centros_Entity
import com.advantys.brsmovilidaderp.R


class Centros_Adapter(private val centrosList: List<Centros_Entity?>): RecyclerView.Adapter<Centros_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Centros_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Centros_ViewHolder(layoutInflater.inflate(R.layout.item_centros, parent, false))
    }

    override fun getItemCount(): Int {

       return centrosList.size
    }

    override fun onBindViewHolder(holder: Centros_ViewHolder, position: Int) {
        val item= centrosList[position]
        holder.render(item)
    }

}


    class Centros_ViewHolder (view: View): RecyclerView.ViewHolder(view){
        val nombreCentro= view.findViewById<TextView>(R.id.nombreCentro)
        val nCentro= view.findViewById<TextView>(R.id.NCENTRO)
        val verDetalles= view.findViewById<ImageButton>(R.id.verDetallesButton)

        fun render(centrosModel: Centros_Entity?){
            nombreCentro.text= centrosModel?.nombre
            nCentro.text= centrosModel?.nCentro.toString()

            verDetalles.setOnClickListener{
                val context= itemView.context
                val intent= Intent(context, DetallesCentro_Activity::class.java)
                context.startActivity(intent)
            }
        }

    }