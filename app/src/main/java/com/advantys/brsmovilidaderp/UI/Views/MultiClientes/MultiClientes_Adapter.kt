package com.advantys.brsmovilidaderp.UI.Views.MultiClientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.MultiCliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.MultiClientes_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemMultiClientesBinding


class MultiClientes_Adapter (val multiclientesList:List<MultiCliente?>, private val multiclienteViewModel: MultiClientes_ViewModel):
    RecyclerView.Adapter<MultiClientes_ViewHolder>() {
    private var elementoSeleccionado: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiClientes_ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return MultiClientes_ViewHolder(layoutInflater.inflate(R.layout.item_multi_clientes, parent, false))
    }

    override fun getItemCount(): Int = multiclientesList.size
    override fun onBindViewHolder(holder: MultiClientes_ViewHolder, position: Int) {
        val item = multiclientesList[position]
        holder.binding.codigomultiCliente.text = item?.multiCliente.toString()
        holder.binding.delegacionMulti.text = item?.multiDelegacion.toString()
       // holder.binding.fabricanteMulti.text = item?.fabricante

        holder.binding.botonDetalles.setOnClickListener {
            multiclienteViewModel.btnDetalle(item, holder.itemView.context)
        }

        holder.itemView.setOnClickListener {
            val elementoSeleccionadoAnterior= elementoSeleccionado
            elementoSeleccionado= holder.adapterPosition
            notifyItemChanged(elementoSeleccionadoAnterior)
            notifyItemChanged(elementoSeleccionado)
            elementoSeleccionado
        }
        holder.bind(multiclientesList[position], position==elementoSeleccionado)

        holder.itemView.setOnLongClickListener {
            val elementoSeleccionadoAnterior= elementoSeleccionado
            elementoSeleccionado= holder.adapterPosition
            notifyItemChanged(elementoSeleccionadoAnterior)
            notifyItemChanged(elementoSeleccionado)
            true
        }
    }
    fun getElementoSeleccionado(): MultiCliente? {
        return if(elementoSeleccionado!=-1) multiclientesList[elementoSeleccionado]
        else null
    }
}

//NO MOSTRAR FABRICANTE POR AHORA
class MultiClientes_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding= ItemMultiClientesBinding.bind(view)
    fun bind(multiClientesModel: MultiCliente?, seleccionado: Boolean) {
        binding.codigomultiCliente.text = multiClientesModel?.multiCliente.toString()
        binding.delegacionMulti.text = multiClientesModel?.multiDelegacion.toString()
        binding.fabricanteMulti.text = multiClientesModel?.fabricante.toString()

        if(seleccionado) itemView.setBackgroundColor(itemView.context.getColor(R.color.colorAdvantysApp6))
        else itemView.setBackgroundColor(itemView.context.getColor(android.R.color.white))
    }
}
