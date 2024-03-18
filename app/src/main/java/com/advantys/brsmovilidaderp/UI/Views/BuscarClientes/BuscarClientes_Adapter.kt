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

        holder.bind(buscarClienteList[position])
        val item = buscarClienteList[position]
        holder.binding.nombreCliente.text = item?.nombre
        holder.binding.codigoCliente.text = item?.numClientes.toString()
        //Realizar seleccionable abajo
    }
    class BuscarClientes_ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemClientesBinding.bind(view)
        fun bind(buscarClientesModel: Cliente?) {
            binding.nombreCliente.text = buscarClientesModel?.nombre
            binding.codigoCliente.text = buscarClientesModel?.numClientes.toString()
            val direccionCompleta =
                "${buscarClientesModel?.direccion} , ${buscarClientesModel?.provincia} , ${buscarClientesModel?.poblacion} "
            binding.direccionCompleta.text = direccionCompleta
        }
    }
}