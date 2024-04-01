package com.advantys.brsmovilidaderp.UI.Views.BuscarArticulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemBuscarArticulosBinding

class BuscarArtictulos_Adapter(private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<BuscarArticulos_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):BuscarArticulos_ViewHolder  {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BuscarArticulos_ViewHolder(layoutInflater.inflate(R.layout.item_buscar_articulos, parent, false))
    }

    override fun onBindViewHolder(holder: BuscarArticulos_ViewHolder, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text= item?.nombre
        holder.binding.codigoArticulo.text= item?.articulo

    }
    override fun getItemCount(): Int = articulosList.size

}

class BuscarArticulos_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemBuscarArticulosBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text= articulosModel?.nombre
        binding.codigoArticulo.text= articulosModel?.articulo
    }

}