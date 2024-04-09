package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemArticulosBinding

class Articulos_Adapter(private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<Articulos_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articulos_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Articulos_ViewHolder(layoutInflater.inflate(R.layout.item_articulos, parent, false))
    }

    override fun getItemCount(): Int = articulosList.size
    override fun onBindViewHolder(holder: Articulos_ViewHolder, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text= item?.nombre
        holder.binding.codigoArticulo.text= item?.articulo
        holder.binding.botonDetalles.setOnClickListener {
            articulosViewModel.btnDetalles(item, holder.itemView.context)
        }

    }
}

class Articulos_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemArticulosBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text= articulosModel?.nombre
        binding.codigoArticulo.text= articulosModel?.articulo
    }

}
