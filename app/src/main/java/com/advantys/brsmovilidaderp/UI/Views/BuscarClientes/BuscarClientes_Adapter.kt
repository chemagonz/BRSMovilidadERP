package com.advantys.brsmovilidaderp.UI.Views.BuscarClientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemClientesBinding

class BuscarClientes_Adapter(val buscarClienteList: List<Cliente?>, private val buscarClienteViewModel: Cliente_ViewModel): RecyclerView.Adapter<BuscarClientes_Adapter.BuscarClientes_ViewHolder>() {

    private var elementoSeleccionado: Int = RecyclerView.NO_POSITION
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BuscarClientes_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BuscarClientes_ViewHolder(
            layoutInflater.inflate(
                R.layout.item_clientes,
                parent,
                false
            )
        )
    }
    override fun getItemCount(): Int = buscarClienteList.size
    override fun onBindViewHolder(holder: BuscarClientes_ViewHolder, position: Int) {
        holder.bind(buscarClienteList[position], position== elementoSeleccionado)
        val item = buscarClienteList[position]
        holder.binding.nombreCliente.text = item?.nombre
        holder.binding.codigoCliente.text = item?.numClientes.toString()

        holder.itemView.setOnClickListener {
            val elementoSeleccionadoAnterior= elementoSeleccionado
            elementoSeleccionado= holder.adapterPosition
            notifyItemChanged(elementoSeleccionadoAnterior)
            notifyItemChanged(elementoSeleccionado)
        }

    }
    class BuscarClientes_ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemClientesBinding.bind(view)
        fun bind(buscarClientesModel: Cliente?, seleccionado: Boolean) {
            binding.nombreCliente.text = buscarClientesModel?.nombre
            binding.codigoCliente.text = buscarClientesModel?.numClientes.toString()
            val direccion= buscarClientesModel?.direccion?: ""
            val provincia= buscarClientesModel?.provincia?: ""
            val poblacion= buscarClientesModel?.poblacion?: ""
            val direccionCompleta = "$direccion $provincia $poblacion"
            binding.direccionCompleta.text = direccionCompleta

            if(seleccionado) itemView.setBackgroundColor(itemView.context.getColor(android.R.color.darker_gray))
            else itemView.setBackgroundColor(itemView.context.getColor(android.R.color.white))
        }
    }
}