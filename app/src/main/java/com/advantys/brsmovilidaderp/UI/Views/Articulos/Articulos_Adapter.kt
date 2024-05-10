package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Articulo
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Articulo_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemArticulosModoColumnaBinding
import com.advantys.brsmovilidaderp.databinding.ItemArticulosModoFotoBinding
import com.advantys.brsmovilidaderp.databinding.ItemArticulosModoListaBinding

class Articulos_Adapter (private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<Articulos_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articulos_ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Articulos_ViewHolder(layoutInflater.inflate(R.layout.item_articulos_modo_lista, parent, false))
    }

    override fun getItemCount(): Int = articulosList.size
    override fun onBindViewHolder(holder: Articulos_ViewHolder, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text = item?.nombre
        holder.binding.codigoArticulo.text = item?.articulo

        holder.binding.botonDetalles.setOnClickListener {
            articulosViewModel.btnDetalles(item, holder.itemView.context)
        }
    }
}

    class Articulos_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemArticulosModoListaBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text = articulosModel?.nombre
        binding.codigoArticulo.text = articulosModel?.articulo
    }

}

class Articulos_Adapter_ModoCatalogoFoto(private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<Articulos_ViewHolder_ModoFoto>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articulos_ViewHolder_ModoFoto {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Articulos_ViewHolder_ModoFoto(layoutInflater.inflate(R.layout.item_articulos_modo_foto, parent, false))
    }

    override fun getItemCount(): Int = articulosList.size
    override fun onBindViewHolder(holder: Articulos_ViewHolder_ModoFoto, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text = item?.nombre
        holder.binding.codigoArticulo.text = item?.articulo
        holder.binding.imagenArticulo.setImageResource(R.mipmap.imagen_prueba_foreground)

        holder.binding.botonDetalles.setOnClickListener {
            articulosViewModel.btnDetalles(item, holder.itemView.context)
        }

    }
}

class Articulos_ViewHolder_ModoFoto(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemArticulosModoFotoBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text = articulosModel?.nombre
        binding.codigoArticulo.text = articulosModel?.articulo
    }

}

class Articulos_Adapter_ModoCatalogoColumna (private val articulosList: List<Articulo?>, private val articulosViewModel: Articulo_ViewModel): RecyclerView.Adapter<Articulos_ViewHolder_ModoColumna>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Articulos_ViewHolder_ModoColumna {

        val layoutInflater = LayoutInflater.from(parent.context)
        return Articulos_ViewHolder_ModoColumna(layoutInflater.inflate(R.layout.item_articulos_modo_columna, parent, false))
    }

    override fun getItemCount(): Int = articulosList.size
    override fun onBindViewHolder(holder: Articulos_ViewHolder_ModoColumna, position: Int) {
        holder.bind(articulosList[position])

        val item = articulosList[position]
        holder.binding.nombreArticulo.text = item?.nombre
        holder.binding.codigoArticulo.text = item?.articulo
        holder.binding.imagenArticulo.setImageResource(R.mipmap.imagen_prueba_foreground)

        holder.binding.botonDetalles.setOnClickListener {
            articulosViewModel.btnDetalles(item, holder.itemView.context)
        }

    }
}

class Articulos_ViewHolder_ModoColumna(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemArticulosModoColumnaBinding.bind(view)
    fun bind(articulosModel: Articulo?){
        binding.nombreArticulo.text = articulosModel?.nombre
        binding.codigoArticulo.text = articulosModel?.articulo
    }

}
