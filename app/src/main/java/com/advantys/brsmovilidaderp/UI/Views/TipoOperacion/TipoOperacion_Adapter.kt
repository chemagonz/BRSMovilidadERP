package com.advantys.brsmovilidaderp.UI.Views.TipoOperacion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.tipoOperacion
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.TipoOperacion_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemOperaciontipoBinding

class TipoOperacion_Adapter (private val tipoOperacionList:List<tipoOperacion?>, private val tipoOperacionViewModel: TipoOperacion_ViewModel): RecyclerView.Adapter<TipoOperacion_ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoOperacion_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TipoOperacion_ViewHolder(layoutInflater.inflate(R.layout.item_operaciontipo, parent, false))
    }

    override fun getItemCount(): Int = tipoOperacionList.size


    override fun onBindViewHolder(holder: TipoOperacion_ViewHolder, position: Int) {
       holder.bind(tipoOperacionList[position])
        val item= tipoOperacionList[position]
        holder.binding.tipoOperacion.text= item?.tipoOperacion.toString()
        holder.binding.nombre.text = item?.nombre
    }
}

class TipoOperacion_ViewHolder(view: View):RecyclerView.ViewHolder(view) {
    val binding= ItemOperaciontipoBinding.bind(view)
    fun bind(tipoOperacionModel: tipoOperacion?){
        binding.nombre.text= tipoOperacionModel?.nombre
        binding.tipoOperacion.text= tipoOperacionModel?.tipoOperacion.toString()
    }
}


