package com.advantys.brsmovilidaderp.UI.Views.Articulos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.advantys.brsmovilidaderp.Domain.Models.Tarifa
import com.advantys.brsmovilidaderp.R
import com.advantys.brsmovilidaderp.UI.ViewModels.Tarifa_ViewModel
import com.advantys.brsmovilidaderp.databinding.ItemTarifaArticuloBinding

class Tarifas_Adapter(private val tarifaList:List<Tarifa?>, private val tarifaViewmodel: Tarifa_ViewModel): RecyclerView.Adapter<Tarifa_ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Tarifa_ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return Tarifa_ViewHolder(layoutInflater.inflate(R.layout.item_tarifa_articulo, parent, false))
    }

    override fun getItemCount(): Int = tarifaList.size

    override fun onBindViewHolder(holder: Tarifa_ViewHolder, position: Int) {
        holder.bind(tarifaList[position])
        val item= tarifaList[position]
        holder.binding.nombreTarifa.text= item?.nombre

    }
}

class Tarifa_ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val binding = ItemTarifaArticuloBinding.bind(view)
    fun bind(tarifaModel: Tarifa?){
        binding.nombreTarifa.text= tarifaModel?.nombre
    }
}