package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemClientesprincipalBinding


class Clientes_Adapter(val clientesList:List<Cliente?>, private val clienteViewModel: Cliente_ViewModel):RecyclerView.Adapter<Clientes_ViewHolder>() {
    private var elementoSeleccionado: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Clientes_ViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return Clientes_ViewHolder(layoutInflater.inflate(R.layout.item_clientesprincipal, parent, false))
    }

    override fun getItemCount(): Int = clientesList.size
    override fun onBindViewHolder(holder: Clientes_ViewHolder, position: Int) {

        holder.bind(clientesList[position], position==elementoSeleccionado)
        val item = clientesList[position]
        holder.binding.nombreCliente.text = item?.nombre
        holder.binding.codigoCliente.text = item?.numClientes.toString()

        holder.itemView.setOnClickListener {
            val elementoSeleccionadoAnterior= elementoSeleccionado
            elementoSeleccionado= holder.adapterPosition
            notifyItemChanged(elementoSeleccionadoAnterior)
            notifyItemChanged(elementoSeleccionado)
        }

    }
}
class Clientes_ViewHolder(view:View):RecyclerView.ViewHolder(view){
    val binding= ItemClientesprincipalBinding.bind(view)
    fun bind(clientesModel: Cliente?, seleccionado: Boolean) {
        binding.nombreCliente.text = clientesModel?.nombre
        binding.codigoCliente.text = clientesModel?.numClientes.toString()

        if(seleccionado) itemView.setBackgroundColor(itemView.context.getColor(android.R.color.darker_gray))
        else itemView.setBackgroundColor(itemView.context.getColor(android.R.color.white))
    }
}
