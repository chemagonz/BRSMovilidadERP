package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.tipoOperacion
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacion_ViewModel

class TipoOperacion_Adapter (private val tipoOperacionList:List<tipoOperacion?>, private val tipoOperacionViewModel: TipoOperacion_ViewModel): RecyclerView.Adapter<TipoOperacion_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoOperacion_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoOperacion_ViewHolder(layoutInflater.inflate(R.layout.item_operaciontipo, parent, false))
    }

    override fun getItemCount(): Int = tipoOperacionList.size


    override fun onBindViewHolder(holder: TipoOperacion_ViewHolder, position: Int) {
       holder.render(tipoOperacionList[position])
        val item= tipoOperacionList[position]
        holder.tipoOperacion.text= item?.tipoOperacion.toString()
        holder.nombre.text = item?.nombre
    }
}

class TipoOperacion_ViewHolder(view: View):RecyclerView.ViewHolder(view) {

    val nombre= view.findViewById<TextView>(R.id.nombre)
    val tipoOperacion= view.findViewById<TextView>(R.id.tipoOperacion)

    fun render(tipoOperacionModel: tipoOperacion?){
        nombre.text= tipoOperacionModel?.nombre
        tipoOperacion.text= tipoOperacionModel?.tipoOperacion.toString()
    }
}


