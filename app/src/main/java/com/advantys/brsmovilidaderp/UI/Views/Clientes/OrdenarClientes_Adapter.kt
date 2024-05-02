package com.advantys.brsmovilidaderp.UI.Views.Clientes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Cliente
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Cliente_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemOrdenarClientesBinding

class OrdenarClientes_Adapter(val ordenarClientesList: MutableList<Cliente?>, private val ordenarClienteViewModel: Cliente_ViewModel): RecyclerView.Adapter<OrdenarClientes_Adapter.OrdenarClientes_ViewHolder>(), MoverCliente.OnItemTouchListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrdenarClientes_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return OrdenarClientes_ViewHolder(layoutInflater.inflate(R.layout.item_ordenar_clientes, parent, false)
        )
    }
    override fun getItemCount(): Int = ordenarClientesList.size
    override fun onBindViewHolder(holder: OrdenarClientes_ViewHolder, position: Int) {
        holder.bind(ordenarClientesList[position])
        val item = ordenarClientesList[position]
        holder.binding.nombreOrdenarCliente.text = item?.nombre
        holder.binding.codigoOrdenarCliente.text = item?.numClientes.toString()
    }

    override fun onItemMove(initialPosition: Int, finalPosition: Int) {
        val cliente = ordenarClientesList.removeAt(initialPosition)
        ordenarClientesList.add(finalPosition, cliente)
        notifyItemMoved(initialPosition, finalPosition)
    }

    class OrdenarClientes_ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemOrdenarClientesBinding.bind(view)
        fun bind(ordenarClientesModel: Cliente?) {
            binding.nombreOrdenarCliente.text = ordenarClientesModel?.nombre
            binding.codigoOrdenarCliente.text = ordenarClientesModel?.numClientes.toString()
        }
    }
}