package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Data.DataBase.Entities.TipoOperacion_Entity
import com.advantys.brsmovilidaderp.R

class TipoOperacion_Adapter (private val tipoOperacionList:List<TipoOperacion_Entity?>): RecyclerView.Adapter<TipoOperacion_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoOperacion_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoOperacion_ViewHolder(layoutInflater.inflate(R.layout.item_operaciontipo, parent, false))
    }

    override fun getItemCount(): Int {
        return tipoOperacionList.size
    }

    override fun onBindViewHolder(holder: TipoOperacion_ViewHolder, position: Int) {
        val item = tipoOperacionList[position]
        holder.render(item)
    }
}

class TipoOperacion_ViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val nombre= view.findViewById<TextView>(R.id.nombre)
    val tipoOperacion= view.findViewById<TextView>(R.id.tipoOperacion)

    fun render(tipoOperacionModel: TipoOperacion_Entity?){
        nombre.text= tipoOperacionModel?.nombre
        tipoOperacion.text= tipoOperacionModel?.tipoOperacion.toString()
    }
}


