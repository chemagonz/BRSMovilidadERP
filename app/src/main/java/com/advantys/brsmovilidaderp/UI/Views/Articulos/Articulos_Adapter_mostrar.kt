package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemArticulosMostrarBinding

class Articulos_Adapter_mostrar(private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<Articulos_ViewHolder_mostrar>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articulos_ViewHolder_mostrar {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Articulos_ViewHolder_mostrar(layoutInflater.inflate(R.layout.item_articulos_mostrar, parent, false))
    }

    override fun getItemCount(): Int = articulosList.size
    override fun onBindViewHolder(holder: Articulos_ViewHolder_mostrar, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text= item?.nombre
        holder.binding.codigoArticulo.text= item?.articulo
        holder.binding.imagenArticulo.setImageResource(R.mipmap.imagen_prueba_foreground)

        holder.binding.botonDetalles.setOnClickListener {
            articulosViewModel.btnDetalles(item, holder.itemView.context)
        }

    }
}

class Articulos_ViewHolder_mostrar(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemArticulosMostrarBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text= articulosModel?.nombre
        binding.codigoArticulo.text= articulosModel?.articulo
    }

}
